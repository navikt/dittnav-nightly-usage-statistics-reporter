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
        URL("$eventHandlerBaseURL/stats/total/grouped/bruker")
    )

    suspend fun getActiveEventsPerUser(eventType: EventType): ActiveEventsPerUser = getMeasurement(
        URL("$eventHandlerBaseURL/stats/grouped/bruker/${eventType.eventType}/active")
    )

    suspend fun getTotalActiveEventsPerUser(): ActiveEventsPerUser = getMeasurement(
        URL("$eventHandlerBaseURL/stats/total/grouped/bruker/active")
    )

    suspend fun getEventActiveRate(eventType: EventType): EventActiveRatePerUser = getRateMeasurement(
        URL("$eventHandlerBaseURL/stats/grouped/bruker/${eventType.eventType}/active-rate")
    )

    suspend fun getTotalEventActiveRatePerUser(): EventActiveRatePerUser = getRateMeasurement(
        URL("$eventHandlerBaseURL/stats/total/grouped/bruker/active-rate")
    )

    suspend fun getEventsPerGroupId(eventType: EventType): EventsPerGroupId = getMeasurement(
        URL("$eventHandlerBaseURL/stats/grouped/gruppering/${eventType.eventType}")
    )

    suspend fun getTotalEventsPerGroupId(): EventsPerGroupId = getMeasurement(
        URL("$eventHandlerBaseURL/stats/total/grouped/gruppering")
    )

    suspend fun getGroupIdsPerUser(eventType: EventType): GroupIdsPerUser = getMeasurement(
        URL("$eventHandlerBaseURL/stats/grouped/bruker/${eventType.eventType}/grupperings")
    )

    suspend fun getTotalGroupIdsPerUser(): GroupIdsPerUser = getMeasurement(
        URL("$eventHandlerBaseURL/stats/total/grouped/bruker/grupperings")
    )

    suspend fun getEventTextLength(eventType: EventType): EventTextLength = getMeasurement(
        URL("$eventHandlerBaseURL/stats/${eventType.eventType}/text-length")
    )

    suspend fun getTotalEventTextLength(): EventTextLength = getMeasurement(
        URL("$eventHandlerBaseURL/stats/total/text-length")
    )

    suspend fun getNumberOfUsersWithEvents(eventType: EventType): Int = getCount(
        URL("$eventHandlerBaseURL/stats/${eventType.eventType}/bruker-count")
    )

    suspend fun getTotalNumberOfUsersWithEvents(): Int = getCount(
        URL("$eventHandlerBaseURL/stats/total/bruker-count")
    )

    suspend fun getNumberOfEvents(eventType: EventType): Int = getCount(
        URL("$eventHandlerBaseURL/stats/count/${eventType.eventType}")
    )

    suspend fun getTotalNumberOfEvents(): Int = getCount(
        URL("$eventHandlerBaseURL/stats/total/count")
    )

    suspend fun getNumberOfActiveEvents(eventType: EventType): Int = getCount(
        URL("$eventHandlerBaseURL/stats/count/${eventType.eventType}/active")
    )

    suspend fun getTotalNumberOfActiveEvents(): Int = getCount(
        URL("$eventHandlerBaseURL/stats/total/count/active")
    )

    private suspend fun getRateMeasurement(url: URL): DecimalMeasurement =
        client.get(url, azureTokenFetcher)

    private suspend fun getMeasurement(url: URL): IntegerMeasurement =
        client.get(url, azureTokenFetcher)

    private suspend fun getCount(url: URL): Int =
        client.get<CountMeasurement>(url, azureTokenFetcher).count
}
