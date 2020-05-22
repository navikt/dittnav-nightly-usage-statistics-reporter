package no.nav.personbruker.dittnav.metrics.reporting

import no.nav.personbruker.dittnav.metrics.database.entity.IntegerMeasurement
import no.nav.personbruker.dittnav.metrics.database.exception.RetriableDatabaseException
import org.slf4j.LoggerFactory

private const val DATABASE_FETCH_MAX_ATTEMPTS = 3

private val log = LoggerFactory.getLogger("ExceptionSafeReportingKT")

suspend fun tryFetch(provider: suspend () -> IntegerMeasurement): MeasurementResult<IntegerMeasurement> {
    var attempts = 0

    while (true) {
        try {
            val result = provider.invoke()
            return MeasurementResult(result)
        } catch (e: RetriableDatabaseException) {
            attempts++

            if (attempts < DATABASE_FETCH_MAX_ATTEMPTS) {
                log.warn("Fikk periodisk feil ved henting av data fra databasen. Prøver på nytt.", e)
            } else {
                log.warn("Fikk periodisk feil ved henting av data fra databasen. Ga opp etter $attempts forsøk.", e)
                return failedResult()
            }
        } catch (e: Exception) {
            log.warn("Fikk uventet feil ved henting av data fra databasen", e)
            return failedResult()
        }
    }
}

private fun <T> failedResult(): MeasurementResult<T> = MeasurementResult(null)


class MeasurementResult<T> (val result: T?) {
    suspend fun onSuccess(consumer: suspend (T) -> Unit): MeasurementResult<T> {
        if (result != null) {
            consumer.invoke(result)
        }

        return this
    }

    suspend fun onFailure(consumer: suspend () -> Unit): MeasurementResult<T> {
        if (result == null) {
            consumer.invoke()
        }

        return this
    }
}