package no.nav.personbruker.dittnav.metrics.config

import io.mockk.coEvery
import io.mockk.mockk
import no.nav.personbruker.dittnav.metrics.collectors.BeskjedMetricsCollector
import no.nav.personbruker.dittnav.metrics.common.StatisticsService
import no.nav.personbruker.dittnav.metrics.common.DecimalMeasurement
import no.nav.personbruker.dittnav.metrics.common.IntegerMeasurement
import no.nav.personbruker.dittnav.metrics.collectors.DoneMetricsCollector
import no.nav.personbruker.dittnav.metrics.collectors.InnboksMetricsCollector
import no.nav.personbruker.dittnav.metrics.collectors.OppgaveMetricsCollector
import no.nav.personbruker.dittnav.metrics.reporting.MeasurementCollector
import no.nav.personbruker.dittnav.metrics.reporting.buildMetricsReporter
import no.nav.personbruker.dittnav.metrics.collectors.TotalEventsMetricsCollector

class TestApplicationContext: ApplicationContext {

    private val environment = testEnvironment()

    private val metricsReporter = buildMetricsReporter(environment)
    private val measurementCollector = MeasurementCollector(metricsReporter)

    private val mockIntMeasurement = IntegerMeasurement(
        1, 2, 1.5, 1, 1, 1, 1, 1
    )
    private val mockDecimalMeasurement = DecimalMeasurement(
        1.0, 2.0, 1.5, 1.0, 1.0, 1.0, 1.0, 1.0
    )
    private val statisticsService = mockk<StatisticsService>(relaxed = true).also {
        coEvery { it.getEventsPerUser(any()) } returns mockIntMeasurement
        coEvery { it.getActiveEventsPerUser(any()) } returns mockIntMeasurement
        coEvery { it.getEventActiveRate(any()) } returns mockDecimalMeasurement
        coEvery { it.getEventsPerGroupId(any()) } returns mockIntMeasurement
        coEvery { it.getGroupIdsPerUser(any()) } returns mockIntMeasurement
        coEvery { it.getEventTextLength(any()) } returns mockIntMeasurement
        coEvery { it.getNumberOfUsersWithEvents(any()) } returns 1
        coEvery { it.getNumberOfEvents(any()) } returns 1
        coEvery { it.getNumberOfActiveEvents(any()) } returns 1

        coEvery { it.getTotalEventsPerUser() } returns mockIntMeasurement
        coEvery { it.getTotalActiveEventsPerUser() } returns mockIntMeasurement
        coEvery { it.getTotalEventActiveRatePerUser() } returns mockDecimalMeasurement
        coEvery { it.getTotalEventsPerGroupId() } returns mockIntMeasurement
        coEvery { it.getTotalGroupIdsPerUser() } returns mockIntMeasurement
        coEvery { it.getTotalEventTextLength() } returns mockIntMeasurement
        coEvery { it.getTotalNumberOfUsersWithEvents() } returns 1
        coEvery { it.getTotalNumberOfEvents() } returns 1
        coEvery { it.getTotalNumberOfActiveEvents() } returns 1
    }
    override val beskjedMetricsCollector = BeskjedMetricsCollector(statisticsService, measurementCollector)
    override val oppgaveMetricsCollector = OppgaveMetricsCollector(statisticsService, measurementCollector)
    override val innboksMetricsCollector = InnboksMetricsCollector(statisticsService, measurementCollector)
    override val doneMetricsCollector = DoneMetricsCollector(statisticsService, measurementCollector)
    override val totalEventsMetricsCollector = TotalEventsMetricsCollector(statisticsService, measurementCollector)

}

private fun testEnvironment() = Environment(
    dbHost = "N/A",
    dbName = "N/A",
    dbUser = "N/A",
    dbUrl = "N/A",
    dbPort = "123",
    dbPassword = "N/A",
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
