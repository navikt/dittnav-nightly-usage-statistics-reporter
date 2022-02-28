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
        URL("$eventHandlerBaseURL/stats/grouped/bruker/${EventType.BESKJED.eventType}")
    )

    suspend fun getActiveBeskjedEventsPerUser(): ActiveEventsPerUser = getMeasurement(
        URL("$eventHandlerBaseURL/stats/grouped/bruker/${EventType.BESKJED.eventType}/active")
    )

    suspend fun getBeskjedEventActiveRate(): EventActiveRatePerUser = getRateMeasurement(
        URL("$eventHandlerBaseURL/stats/grouped/bruker/${EventType.BESKJED.eventType}/active-rate")
    )

    suspend fun getBeskjedEventsPerGroupId(): EventsPerGroupId = getMeasurement(
        URL("$eventHandlerBaseURL/stats/grouped/gruppering/${EventType.BESKJED.eventType}")
    )

    suspend fun getBeskjedGroupIdsPerUser(): GroupIdsPerUser = getMeasurement(
        URL("$eventHandlerBaseURL/stats/grouped/bruker/${EventType.BESKJED.eventType}/grupperings")
    )

    suspend fun getBeskjedEventTextLength(): EventTextLength = getMeasurement(
        URL("$eventHandlerBaseURL/stats/${EventType.BESKJED.eventType}/text-length")
    )

    suspend fun getNumberOfUsersWithBeskjedEvents(): Int = getCount(
        URL("$eventHandlerBaseURL/stats/${EventType.BESKJED.eventType}/bruker-count")
    )

    suspend fun getNumberOfBeskjedEvents(): Int = getCount(
        URL("$eventHandlerBaseURL/stats/${EventType.BESKJED.eventType}")
    )

    suspend fun getNumberOfActiveBeskjedEvents(): Int = getCount(
        URL("$eventHandlerBaseURL/stats/${EventType.BESKJED.eventType}/active")
    )

    private suspend fun getRateMeasurement(url: URL): DecimalMeasurement =
        client.get(url, azureTokenFetcher)

    private suspend fun getMeasurement(url: URL): IntegerMeasurement =
        client.get(url, azureTokenFetcher)

    private suspend fun getCount(url: URL): Int =
        client.get<CountMeasurement>(url, azureTokenFetcher).count
}
