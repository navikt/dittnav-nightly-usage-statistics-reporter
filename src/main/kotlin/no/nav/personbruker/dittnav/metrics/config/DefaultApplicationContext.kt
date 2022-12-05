package no.nav.personbruker.dittnav.metrics.config

import no.nav.personbruker.dittnav.metrics.collectors.BeskjedMetricsCollector
import no.nav.personbruker.dittnav.metrics.common.StatisticsService
import no.nav.personbruker.dittnav.metrics.collectors.InnboksMetricsCollector
import no.nav.personbruker.dittnav.metrics.collectors.OppgaveMetricsCollector
import no.nav.personbruker.dittnav.metrics.reporting.MeasurementCollector
import no.nav.personbruker.dittnav.metrics.reporting.buildMetricsReporter
import no.nav.personbruker.dittnav.metrics.collectors.TotalEventsMetricsCollector

class DefaultApplicationContext: ApplicationContext {

    private val environment = Environment()

    private val metricsReporter = buildMetricsReporter(environment)
    private val measurementCollector = MeasurementCollector(metricsReporter)

    private val httpClient = HttpClientBuilder.build()
    private val azureTokenFetcher = AzureTokenFetcher(environment.eventHandlerAppEnvironmentDetails)
    private val statisticsService = StatisticsService(httpClient, azureTokenFetcher, environment.eventHandlerURL)

    override val beskjedMetricsCollector = BeskjedMetricsCollector(statisticsService, measurementCollector)
    override val oppgaveMetricsCollector = OppgaveMetricsCollector(statisticsService, measurementCollector)
    override val innboksMetricsCollector = InnboksMetricsCollector(statisticsService, measurementCollector)
    override val totalEventsMetricsCollector = TotalEventsMetricsCollector(statisticsService, measurementCollector)
}
