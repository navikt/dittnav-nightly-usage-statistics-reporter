package no.nav.personbruker.dittnav.metrics.config

import no.nav.personbruker.dittnav.metrics.beskjed.BeskjedMetricsCollector
import no.nav.personbruker.dittnav.metrics.beskjed.BeskjedStatisticsService
import no.nav.personbruker.dittnav.metrics.database.Database
import no.nav.personbruker.dittnav.metrics.database.H2Database
import no.nav.personbruker.dittnav.metrics.database.PostgresDatabase
import no.nav.personbruker.dittnav.metrics.done.DoneMetricsCollector
import no.nav.personbruker.dittnav.metrics.done.DoneRepository
import no.nav.personbruker.dittnav.metrics.innboks.InnboksMetricsCollector
import no.nav.personbruker.dittnav.metrics.innboks.InnboksRepository
import no.nav.personbruker.dittnav.metrics.oppgave.OppgaveMetricsCollector
import no.nav.personbruker.dittnav.metrics.oppgave.OppgaveRepository
import no.nav.personbruker.dittnav.metrics.reporting.MeasurementCollector
import no.nav.personbruker.dittnav.metrics.reporting.buildMetricsReporter
import no.nav.personbruker.dittnav.metrics.total.TotalEventsRepository
import no.nav.personbruker.dittnav.metrics.total.TotalEventsMetricsCollector

class DefaultApplicationContext: ApplicationContext {

    private val environment = Environment()
    private val database: Database = H2Database()

    private val metricsReporter = buildMetricsReporter(environment)
    private val measurementCollector = MeasurementCollector(metricsReporter)

    private val httpClient = HttpClientBuilder.build()
    private val azureTokenFetcher = AzureTokenFetcher(environment.eventHandlerAppEnvironmentDetails)
    private val beskjedRepository = BeskjedStatisticsService(httpClient, azureTokenFetcher, environment.eventHandlerURL)
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
