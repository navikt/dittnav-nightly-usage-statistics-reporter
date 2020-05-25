package no.nav.personbruker.dittnav.metrics.reporting

import no.nav.personbruker.dittnav.metrics.database.exception.RetriableDatabaseException
import org.slf4j.LoggerFactory

private const val DATABASE_FETCH_MAX_ATTEMPTS = 3

private val log = LoggerFactory.getLogger("ExceptionSafeReportingKT")

suspend fun <T> tryFetch(provider: suspend () -> T): MeasurementResult<T> {
    var attempts = 0
    val startTime = System.currentTimeMillis()

    while (true) {
        try {
            val result = provider.invoke()
            val processingTime = startTime.timeLapsedSinceMillis()

            return MeasurementResult(result, processingTime)
        } catch (e: RetriableDatabaseException) {
            attempts++

            if (attempts < DATABASE_FETCH_MAX_ATTEMPTS) {
                log.warn("Fikk periodisk feil ved henting av data fra databasen. Prøver på nytt.", e)
            } else {
                log.warn("Fikk periodisk feil ved henting av data fra databasen. Ga opp etter $attempts forsøk.", e)
                return failedResult(startTime.timeLapsedSinceMillis())
            }
        } catch (e: Exception) {
            log.warn("Fikk uventet feil ved henting av data fra databasen", e)
            return failedResult(startTime.timeLapsedSinceMillis())
        }
    }
}

private fun <T> failedResult(processingTime: Long): MeasurementResult<T> = MeasurementResult(null, processingTime)


class MeasurementResult<T> (val result: T?, val processingTime: Long) {
    suspend fun onSuccess(consumer: suspend (T, Long) -> Unit): MeasurementResult<T> {
        if (result != null) {
            consumer.invoke(result, processingTime)
        }

        return this
    }

    suspend fun onFailure(consumer: suspend (Long) -> Unit): MeasurementResult<T> {
        if (result == null) {
            consumer.invoke(processingTime)
        }

        return this
    }
}

private fun Long.timeLapsedSinceMillis() = System.currentTimeMillis() - this