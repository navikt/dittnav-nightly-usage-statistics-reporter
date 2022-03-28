package no.nav.personbruker.dittnav.metrics

import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.http.*
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import no.nav.personbruker.dittnav.metrics.common.EventType
import no.nav.personbruker.dittnav.metrics.common.StatisticsService
import no.nav.personbruker.dittnav.metrics.config.AzureTokenFetcher
import no.nav.personbruker.dittnav.metrics.config.Environment
import no.nav.personbruker.dittnav.metrics.config.HttpClientBuilder
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class FrequencyDistributionStatisticsTest {

    @Test
    fun `Frekvensfordeling av antall aktive beskjeder`() {

        val httpClient: HttpClient = HttpClientBuilder.build(MockEngine() {
            _ -> respond("""{"eventFrequencies": [
                {"antallEventer": 1, "antallBrukere": 432},
                {"antallEventer": 3, "antallBrukere": 12},
                {"antallEventer": 10, "antallBrukere": 1},
                {"antallEventer": 12, "antallBrukere": 2},
                {"antallEventer": 14, "antallBrukere": 1},
                {"antallEventer": 15, "antallBrukere": 3},
                {"antallEventer": 16, "antallBrukere": 2},
                {"antallEventer": 27, "antallBrukere": 3},
                {"antallEventer": 30, "antallBrukere": 3}
                ]}""".trimMargin(), HttpStatusCode.OK, headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString()))
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

    private fun testEnvironment() = Environment(
        clusterName = "N/A",
        namespace = "N/A",
        appnavn = "N/A",
        influxdbHost = "",
        influxdbPort = 8000,
        influxdbName = "",
        influxdbUser = "",
        influxdbPassword = "",
        influxdbRetentionPolicy = "",
        eventHandlerURL = "",
        eventHandlerAppEnvironmentDetails = "",
    )
}

