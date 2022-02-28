package no.nav.personbruker.dittnav.metrics.config

import io.mockk.coEvery
import io.mockk.mockk
import no.nav.personbruker.dittnav.metrics.beskjed.BeskjedMetricsCollector
import no.nav.personbruker.dittnav.metrics.beskjed.BeskjedStatisticsService
import no.nav.personbruker.dittnav.metrics.database.Database
import no.nav.personbruker.dittnav.metrics.database.H2Database
import no.nav.personbruker.dittnav.metrics.database.entity.IntegerMeasurement
import no.nav.personbruker.dittnav.metrics.done.DoneMetricsCollector
import no.nav.personbruker.dittnav.metrics.done.DoneRepository
import no.nav.personbruker.dittnav.metrics.innboks.InnboksMetricsCollector
import no.nav.personbruker.dittnav.metrics.innboks.InnboksRepository
import no.nav.personbruker.dittnav.metrics.oppgave.OppgaveMetricsCollector
import no.nav.personbruker.dittnav.metrics.oppgave.OppgaveRepository
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
    private val beskjedStatisticsService = mockk<BeskjedStatisticsService>(relaxed = true).also {
        coEvery { it.getBeskjedEventsPerUser() } returns mockIntMeasurement
        coEvery { it.getActiveBeskjedEventsPerUser() } returns mockIntMeasurement
        coEvery { it.getBeskjedEventActiveRate() } returns mockDecimalMeasurement
        coEvery { it.getBeskjedEventsPerGroupId() } returns mockIntMeasurement
        coEvery { it.getBeskjedGroupIdsPerUser() } returns mockIntMeasurement
        coEvery { it.getBeskjedEventTextLength() } returns mockIntMeasurement
        coEvery { it.getNumberOfUsersWithBeskjedEvents() } returns 1
        coEvery { it.getNumberOfBeskjedEvents() } returns 1
        coEvery { it.getNumberOfActiveBeskjedEvents() } returns 1
    }
    override val beskjedMetricsCollector = BeskjedMetricsCollector(beskjedStatisticsService, measurementCollector)

    private val oppgaveRepository = OppgaveRepository(database)
    override val oppgaveMetricsCollector = OppgaveMetricsCollector(oppgaveRepository, measurementCollector)

    private val innboksRepository = InnboksRepository(database)
    override val innboksMetricsCollector = InnboksMetricsCollector(innboksRepository, measurementCollector)

    private val brukernotifikasjonRepository = TotalEventsRepository(database)
    override val totalEventsMetricsCollector = TotalEventsMetricsCollector(brukernotifikasjonRepository, measurementCollector)

    private val doneRepository = DoneRepository(database)
    override val doneMetricsCollector = DoneMetricsCollector(doneRepository, measurementCollector)
}

private fun testEnvironment() = Environment(
    dbHost = "N/A",
    dbName = "N/A",
    dbAdmin = "N/A",
    dbUser = "N/A",
    dbUrl = "N/A",
    dbMountPath = "N/A",
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
