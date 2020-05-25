package no.nav.personbruker.dittnav.metrics.config

import no.nav.personbruker.dittnav.metrics.beskjed.BeskjedMetricsCollector
import no.nav.personbruker.dittnav.metrics.beskjed.BeskjedRepository
import no.nav.personbruker.dittnav.metrics.database.Database
import no.nav.personbruker.dittnav.metrics.database.PostgresDatabase
import no.nav.personbruker.dittnav.metrics.done.DoneMetricsCollector
import no.nav.personbruker.dittnav.metrics.done.DoneRepository
import no.nav.personbruker.dittnav.metrics.innboks.InnboksMetricsCollector
import no.nav.personbruker.dittnav.metrics.innboks.InnboksRepository
import no.nav.personbruker.dittnav.metrics.oppgave.OppgaveMetricsCollector
import no.nav.personbruker.dittnav.metrics.oppgave.OppgaveRepository
import no.nav.personbruker.dittnav.metrics.reporting.MeasurementCollector
import no.nav.personbruker.dittnav.metrics.reporting.buildMetricsReporter
import no.nav.personbruker.dittnav.metrics.total.BrukernotifikasjonRepository
import no.nav.personbruker.dittnav.metrics.total.TotalEventsMetricsCollector

class ApplicationContext {

    private val environment = Environment()
    private val database: Database = PostgresDatabase(environment)

    private val metricsReporter = buildMetricsReporter(environment)
    val measurementCollector = MeasurementCollector(metricsReporter)

    private val beskjedRepository = BeskjedRepository(database)
    val beskjedMetricsCollector = BeskjedMetricsCollector(beskjedRepository, measurementCollector)

    private val oppgaveRepository = OppgaveRepository(database)
    val oppgaveMetricsCollector = OppgaveMetricsCollector(oppgaveRepository, measurementCollector)

    private val innboksRepository = InnboksRepository(database)
    val innboksMetricsCollector = InnboksMetricsCollector(innboksRepository, measurementCollector)

    private val brukernotifikasjonRepository = BrukernotifikasjonRepository(database)
    val totalEventsMetricsCollector = TotalEventsMetricsCollector(brukernotifikasjonRepository, measurementCollector)

    private val doneRepository = DoneRepository(database)
    val doneMetricsCollector = DoneMetricsCollector(doneRepository, measurementCollector)

}