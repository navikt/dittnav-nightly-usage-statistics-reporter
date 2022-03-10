package no.nav.personbruker.dittnav.metrics.reporting

import io.ktor.client.features.ClientRequestException
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.delay
import org.slf4j.LoggerFactory

private const val FETCH_MAX_ATTEMPTS = 3

private val log = LoggerFactory.getLogger("ExceptionSafeReportingKT")

suspend fun <T> tryFetch(provider: suspend () -> T): MeasurementResult<T> {
    val startTime = System.currentTimeMillis()
    var attempts = 0

    while (true) {
        try {
            val result = provider.invoke()
            val processingTime = startTime.timeLapsedSinceMillis()

            return MeasurementResult(result, processingTime)
        } catch (e: ClientRequestException) {
            if (e.response.status == HttpStatusCode.Unauthorized) {
                attempts++

                if (attempts < FETCH_MAX_ATTEMPTS) {
                    log.warn("Fikk auth feil ved henting av data. Prøver på nytt om 5s.", e)
                    delay(5 * 1000)
                } else {
                    log.warn("Fikk auth feil ved henting av data. Ga opp etter $attempts forsøk.", e)
                    return failedResult(startTime.timeLapsedSinceMillis())
                }
            } else {
                log.warn("Fikk uventet HTTP feil ved henting av data. Ga opp etter $attempts forsøk.", e)
                return failedResult(startTime.timeLapsedSinceMillis())
            }
        }
        catch (e: Exception) {
            log.warn("Fikk uventet feil ved henting av data", e)
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
