package no.nav.personbruker.dittnav.metrics.beskjed

import io.ktor.client.HttpClient
import no.nav.personbruker.dittnav.metrics.config.AzureTokenFetcher
import no.nav.personbruker.dittnav.metrics.config.EventType
import no.nav.personbruker.dittnav.metrics.config.get
import no.nav.personbruker.dittnav.metrics.database.entity.*
import java.net.URL

class BeskjedStatisticsService(
    private val client: HttpClient,
    private val azureTokenFetcher: AzureTokenFetcher,
    private val eventHandlerBaseURL: String
) {

    suspend fun getBeskjedEventsPerUser(): EventsPerUser = getMeasurement(
        URL("$eventHandlerBaseURL/fetch/grouped/producer/${EventType.BESKJED.eventType}")
    )

    suspend fun getVisibleBeskjedEventsPerUser(): VisibleEventsPerUser = getMeasurement(
        URL("$eventHandlerBaseURL/fetch/grouped/producer/${EventType.BESKJED.eventType}")
    )

    suspend fun getActiveBeskjedEventsPerUser(): ActiveEventsPerUser = getMeasurement(
        URL("$eventHandlerBaseURL/fetch/grouped/producer/${EventType.BESKJED.eventType}")
    )

    suspend fun getBeskjedEventActiveRate(): EventActiveRatePerUser = getRateMeasurement(
        URL("$eventHandlerBaseURL/fetch/grouped/producer/${EventType.BESKJED.eventType}")
    )

    suspend fun getExpiredBeskjedEventsPerUser(): ExpiredEventsPerUser = getMeasurement(
        URL("$eventHandlerBaseURL/fetch/grouped/producer/${EventType.BESKJED.eventType}")
    )

    suspend fun getBeskjedEventExpiredRate(): EventExpiredRatePerUser = getRateMeasurement(
        URL("$eventHandlerBaseURL/fetch/grouped/producer/${EventType.BESKJED.eventType}")
    )

    suspend fun getExpiredBeskjedEventPerUserByInvisible(): EventExpiredRateByInvisiblePerUser = getRateMeasurement(
        URL("$eventHandlerBaseURL/fetch/grouped/producer/${EventType.BESKJED.eventType}")
    )

    suspend fun getBeskjedEventsPerGroupId(): EventsPerGroupId = getMeasurement(
        URL("$eventHandlerBaseURL/fetch/grouped/producer/${EventType.BESKJED.eventType}")
    )

    suspend fun getBeskjedGroupIdsPerUser(): GroupIdsPerUser = getMeasurement(
        URL("$eventHandlerBaseURL/fetch/grouped/producer/${EventType.BESKJED.eventType}")
    )

    suspend fun getBeskjedEventTextLength(): EventTextLength = getMeasurement(
        URL("$eventHandlerBaseURL/fetch/grouped/producer/${EventType.BESKJED.eventType}")
    )

    suspend fun getNumberOfUsersWithBeskjedEvents(): Int = getCount(
        URL("$eventHandlerBaseURL/fetch/grouped/producer/${EventType.BESKJED.eventType}")
    )

    suspend fun getNumberOfBeskjedEvents(): Int = getCount(
        URL("$eventHandlerBaseURL/fetch/grouped/producer/${EventType.BESKJED.eventType}")
    )

    suspend fun getNumberOfVisibleBeskjedEvents(): Int = getCount(
        URL("$eventHandlerBaseURL/fetch/grouped/producer/${EventType.BESKJED.eventType}")
    )

    suspend fun getNumberOfActiveBeskjedEvents(): Int = getCount(
        URL("$eventHandlerBaseURL/fetch/grouped/producer/${EventType.BESKJED.eventType}")
    )

    suspend fun getNumberOfExpiredBeskjedEvents(): Int = getCount(
        URL("$eventHandlerBaseURL/fetch/grouped/producer/${EventType.BESKJED.eventType}")
    )

    private suspend fun getRateMeasurement(url: URL): DecimalMeasurement =
        client.get(url, azureTokenFetcher)

    private suspend fun getMeasurement(url: URL): IntegerMeasurement =
        client.get(url, azureTokenFetcher)

    private suspend fun getCount(url: URL): Int =
        client.get(url, azureTokenFetcher)
}
