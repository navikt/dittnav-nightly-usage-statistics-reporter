package no.nav.personbruker.dittnav.metrics.common

import io.ktor.client.HttpClient
import no.nav.personbruker.dittnav.metrics.config.AzureTokenFetcher
import no.nav.personbruker.dittnav.metrics.config.get
import java.net.URL

class StatisticsService(
    private val client: HttpClient,
    private val azureTokenFetcher: AzureTokenFetcher,
    private val eventHandlerBaseURL: String
) {

    suspend fun getEventsPerUser(eventType: EventType): EventsPerUser = getMeasurement(
        URL("$eventHandlerBaseURL/stats/grouped/bruker/${eventType.eventType}")
    )

    suspend fun getTotalEventsPerUser(): EventsPerUser = getMeasurement(
        URL("$eventHandlerBaseURL/stats/grouped/bruker")
    )

    suspend fun getActiveEventsPerUser(eventType: EventType): ActiveEventsPerUser = getMeasurement(
        URL("$eventHandlerBaseURL/stats/grouped/bruker/active/${eventType.eventType}")
    )

    suspend fun getTotalActiveEventsPerUser(): ActiveEventsPerUser = getMeasurement(
        URL("$eventHandlerBaseURL/stats/grouped/bruker/active")
    )

    suspend fun getEventActiveRate(eventType: EventType): EventActiveRatePerUser = getRateMeasurement(
        URL("$eventHandlerBaseURL/stats/grouped/bruker/active-rate/${eventType.eventType}")
    )

    suspend fun getTotalEventActiveRatePerUser(): EventActiveRatePerUser = getRateMeasurement(
        URL("$eventHandlerBaseURL/stats/grouped/bruker/active-rate")
    )

    suspend fun getEventsPerGroupId(eventType: EventType): EventsPerGroupId = getMeasurement(
        URL("$eventHandlerBaseURL/stats/grouped/gruppering/${eventType.eventType}")
    )

    suspend fun getTotalEventsPerGroupId(): EventsPerGroupId = getMeasurement(
        URL("$eventHandlerBaseURL/stats/grouped/gruppering")
    )

    suspend fun getGroupIdsPerUser(eventType: EventType): GroupIdsPerUser = getMeasurement(
        URL("$eventHandlerBaseURL/stats/grouped/bruker/grupperings/${eventType.eventType}")
    )

    suspend fun getTotalGroupIdsPerUser(): GroupIdsPerUser = getMeasurement(
        URL("$eventHandlerBaseURL/stats/grouped/bruker/grupperings")
    )

    suspend fun getEventTextLength(eventType: EventType): EventTextLength = getMeasurement(
        URL("$eventHandlerBaseURL/stats/text-length/${eventType.eventType}")
    )

    suspend fun getTotalEventTextLength(): EventTextLength = getMeasurement(
        URL("$eventHandlerBaseURL/stats/text-length")
    )

    suspend fun getNumberOfUsersWithEvents(eventType: EventType): Int = getCount(
        URL("$eventHandlerBaseURL/stats/bruker-count/${eventType.eventType}")
    )

    suspend fun getTotalNumberOfUsersWithEvents(): Int = getCount(
        URL("$eventHandlerBaseURL/stats/bruker-count")
    )

    suspend fun getNumberOfEvents(eventType: EventType): Int = getCount(
        URL("$eventHandlerBaseURL/stats/count/${eventType.eventType}")
    )

    suspend fun getTotalNumberOfEvents(): Int = getCount(
        URL("$eventHandlerBaseURL/stats/count")
    )

    suspend fun getNumberOfActiveEvents(eventType: EventType): Int = getCount(
        URL("$eventHandlerBaseURL/stats/count/active/${eventType.eventType}")
    )

    suspend fun getTotalNumberOfActiveEvents(): Int = getCount(
        URL("$eventHandlerBaseURL/stats/count/active")
    )

    private suspend fun getRateMeasurement(url: URL): DecimalMeasurement =
        client.get(url, azureTokenFetcher)

    private suspend fun getMeasurement(url: URL): IntegerMeasurement =
        client.get(url, azureTokenFetcher)

    private suspend fun getCount(url: URL): Int =
        client.get<CountMeasurement>(url, azureTokenFetcher).count
}
