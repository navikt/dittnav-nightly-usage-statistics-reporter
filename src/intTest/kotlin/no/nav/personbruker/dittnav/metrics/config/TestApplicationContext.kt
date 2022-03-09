package no.nav.personbruker.dittnav.metrics.config

import io.mockk.coEvery
import io.mockk.mockk
import no.nav.personbruker.dittnav.metrics.beskjed.BeskjedMetricsCollector
import no.nav.personbruker.dittnav.metrics.common.StatisticsService
import no.nav.personbruker.dittnav.metrics.database.Database
import no.nav.personbruker.dittnav.metrics.database.H2Database
import no.nav.personbruker.dittnav.metrics.database.entity.DecimalMeasurement
import no.nav.personbruker.dittnav.metrics.database.entity.IntegerMeasurement
import no.nav.personbruker.dittnav.metrics.done.DoneMetricsCollector
import no.nav.personbruker.dittnav.metrics.done.DoneRepository
import no.nav.personbruker.dittnav.metrics.innboks.InnboksMetricsCollector
import no.nav.personbruker.dittnav.metrics.oppgave.OppgaveMetricsCollector
import no.nav.personbruker.dittnav.metrics.reporting.MeasurementCollector
import no.nav.personbruker.dittnav.metrics.reporting.buildMetricsReporter
import no.nav.personbruker.dittnav.metrics.total.TotalEventsMetricsCollector
import no.nav.personbruker.dittnav.metrics.total.TotalEventsRepository

class TestApplicationContext: ApplicationContext {

    private val environment = testEnvironment()
    private val database: Database = H2Database().also {
        it.createTablesAndData()
    }

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
    }
    override val beskjedMetricsCollector = BeskjedMetricsCollector(statisticsService, measurementCollector)
    override val oppgaveMetricsCollector = OppgaveMetricsCollector(statisticsService, measurementCollector)
    override val innboksMetricsCollector = InnboksMetricsCollector(statisticsService, measurementCollector)

    private val brukernotifikasjonRepository = TotalEventsRepository(database)
    override val totalEventsMetricsCollector = TotalEventsMetricsCollector(brukernotifikasjonRepository, measurementCollector)

    private val doneRepository = DoneRepository(database)
    override val doneMetricsCollector = DoneMetricsCollector(doneRepository, measurementCollector)
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
