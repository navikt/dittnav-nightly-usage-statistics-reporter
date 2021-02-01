package no.nav.personbruker.dittnav.metrics.config

import no.nav.personbruker.dittnav.metrics.beskjed.BeskjedMetricsCollector
import no.nav.personbruker.dittnav.metrics.beskjed.BeskjedRepository
import no.nav.personbruker.dittnav.metrics.database.Database
import no.nav.personbruker.dittnav.metrics.database.H2Database
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
    private val database: Database = H2Database()

    private val metricsReporter = buildMetricsReporter(environment)
    private val measurementCollector = MeasurementCollector(metricsReporter)

    private val beskjedRepository = BeskjedRepository(database)
    override val beskjedMetricsCollector = BeskjedMetricsCollector(beskjedRepository, measurementCollector)

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
        dbAdmin = "N/A",
        dbHost = "N/A",
        dbMountPath = "N/A",
        dbName = "N/A",
        dbUrl = "N/A",
        dbUser = "N/A",
        clusterName = "N/A",
        sensuHost = "stub",
        sensuPort = 0
    )
