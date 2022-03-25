package no.nav.personbruker.dittnav.metrics

import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import no.nav.personbruker.dittnav.metrics.collectors.BeskjedMetricsCollector
import no.nav.personbruker.dittnav.metrics.common.EventType
import no.nav.personbruker.dittnav.metrics.common.StatisticsService
import no.nav.personbruker.dittnav.metrics.config.AzureTokenFetcher
import no.nav.personbruker.dittnav.metrics.config.Environment
import no.nav.personbruker.dittnav.metrics.config.HttpClientBuilder
import no.nav.personbruker.dittnav.metrics.reporting.MeasurementCollector
import no.nav.personbruker.dittnav.metrics.reporting.buildMetricsReporter
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class FrequencyDistributionStatisticsTest {


    @Test
    fun `Frekvensfordeling av antall aktive beskjeder`() {
        val environment = testEnvironment()

        val metricsReporter = buildMetricsReporter(environment)
        val measurementCollector = MeasurementCollector(metricsReporter)

        val httpClient: HttpClient = HttpClientBuilder.build(MockEngine() {
            _ -> respond("""{"eventFrequencies": [
                {"antallEventer": 1, "antallBrukere": 432},
                {"antallEventer": 3, "antallBrukere": 12},
                {"antallEventer": 10, "antallBrukere": 1},
                {"antallEventer": 12, "antallBrukere": 1},
                {"antallEventer": 14, "antallBrukere": 1}
                ]}""".trimMargin(), HttpStatusCode.OK, headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString()))
        })
        val azureTokenFetcher: AzureTokenFetcher = mockk(relaxed = true)
        val statisticsService = StatisticsService(httpClient, azureTokenFetcher, "http://localhost")
        val metricsCollector = BeskjedMetricsCollector(statisticsService, measurementCollector)
        runBlocking {
            val beskjedFrequencyDistribution = statisticsService.getActiveEventsFrequencyDistribution(EventType.BESKJED)
            val groupedFrequencyDistribution = beskjedFrequencyDistribution.getGroupedFrequencyDistribution()
            groupedFrequencyDistribution.size shouldBeEqualTo  3
            groupedFrequencyDistribution["1"] shouldBeEqualTo 432
            groupedFrequencyDistribution["10-15"] shouldBeEqualTo 3
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

