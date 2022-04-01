package no.nav.personbruker.dittnav.metrics

import com.fasterxml.jackson.databind.ObjectMapper
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.http.*
import io.mockk.MockKSettings.relaxed
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import no.nav.personbruker.dittnav.metrics.common.EventFrequencyDistribution
import no.nav.personbruker.dittnav.metrics.common.EventType
import no.nav.personbruker.dittnav.metrics.common.NumberOfEventsFrequency
import no.nav.personbruker.dittnav.metrics.common.StatisticsService
import no.nav.personbruker.dittnav.metrics.config.AzureTokenFetcher
import no.nav.personbruker.dittnav.metrics.config.Environment
import no.nav.personbruker.dittnav.metrics.config.HttpClientBuilder
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class FrequencyDistributionStatisticsTest {

    @Test
    fun `Frekvensfordeling av antall aktive beskjeder`() {
        val eventFrequencyDistribution = EventFrequencyDistribution(
            listOf(
                NumberOfEventsFrequency(1, 432),
                NumberOfEventsFrequency(3, 12),
                NumberOfEventsFrequency(10, 1),
                NumberOfEventsFrequency(12, 2),
                NumberOfEventsFrequency(14, 1),
                NumberOfEventsFrequency(15, 3),
                NumberOfEventsFrequency(16, 2),
                NumberOfEventsFrequency(27, 3),
                NumberOfEventsFrequency(30, 3)
            )
        )

        val httpClient: HttpClient = HttpClientBuilder.build(MockEngine() {
            respond(
                ObjectMapper().writeValueAsString(eventFrequencyDistribution),
                HttpStatusCode.OK,
                headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            )
        })
        val azureTokenFetcher: AzureTokenFetcher = mockk(relaxed = true)
        val statisticsService = StatisticsService(httpClient, azureTokenFetcher, "http://localhost")

        runBlocking {
            val beskjedFrequencyDistribution = statisticsService.getActiveEventsFrequencyDistribution(EventType.BESKJED)
            val groupedFrequencyDistribution = beskjedFrequencyDistribution.getGroupedFrequencyDistribution()
            groupedFrequencyDistribution.size shouldBeEqualTo 5
            groupedFrequencyDistribution["1"] shouldBeEqualTo 432
            groupedFrequencyDistribution["10-14"] shouldBeEqualTo 4
            groupedFrequencyDistribution["15-19"] shouldBeEqualTo 5
            groupedFrequencyDistribution["20+"] shouldBeEqualTo 6
        }
    }
}

