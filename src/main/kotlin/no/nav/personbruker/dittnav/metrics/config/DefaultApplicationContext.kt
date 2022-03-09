package no.nav.personbruker.dittnav.metrics.config

import no.nav.personbruker.dittnav.metrics.beskjed.BeskjedMetricsCollector
import no.nav.personbruker.dittnav.metrics.common.StatisticsService
import no.nav.personbruker.dittnav.metrics.database.Database
import no.nav.personbruker.dittnav.metrics.database.H2Database
import no.nav.personbruker.dittnav.metrics.done.DoneMetricsCollector
import no.nav.personbruker.dittnav.metrics.done.DoneRepository
import no.nav.personbruker.dittnav.metrics.innboks.InnboksMetricsCollector
import no.nav.personbruker.dittnav.metrics.oppgave.OppgaveMetricsCollector
import no.nav.personbruker.dittnav.metrics.reporting.MeasurementCollector
import no.nav.personbruker.dittnav.metrics.reporting.buildMetricsReporter
import no.nav.personbruker.dittnav.metrics.total.TotalEventsMetricsCollector
import no.nav.personbruker.dittnav.metrics.total.TotalEventsRepository

class DefaultApplicationContext: ApplicationContext {

    private val environment = Environment()
    private val database: Database = H2Database()

    private val metricsReporter = buildMetricsReporter(environment)
    private val measurementCollector = MeasurementCollector(metricsReporter)

    private val httpClient = HttpClientBuilder.build()
    private val azureTokenFetcher = AzureTokenFetcher(environment.eventHandlerAppEnvironmentDetails)
    private val statisticsService = StatisticsService(httpClient, azureTokenFetcher, environment.eventHandlerURL)

    override val beskjedMetricsCollector = BeskjedMetricsCollector(statisticsService, measurementCollector)
    override val oppgaveMetricsCollector = OppgaveMetricsCollector(statisticsService, measurementCollector)
    override val innboksMetricsCollector = InnboksMetricsCollector(statisticsService, measurementCollector)

    private val brukernotifikasjonRepository = TotalEventsRepository(database)
    override val totalEventsMetricsCollector = TotalEventsMetricsCollector(brukernotifikasjonRepository, measurementCollector)

    private val doneRepository = DoneRepository(database)
    override val doneMetricsCollector = DoneMetricsCollector(doneRepository, measurementCollector)

}
