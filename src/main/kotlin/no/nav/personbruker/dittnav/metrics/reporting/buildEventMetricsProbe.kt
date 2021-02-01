package no.nav.personbruker.dittnav.metrics.reporting

import no.nav.personbruker.dittnav.common.metrics.MetricsReporter
import no.nav.personbruker.dittnav.common.metrics.StubMetricsReporter
import no.nav.personbruker.dittnav.common.metrics.influx.InfluxMetricsReporter
import no.nav.personbruker.dittnav.common.metrics.influx.SensuConfig
import no.nav.personbruker.dittnav.metrics.config.Environment

fun buildMetricsReporter(environment: Environment): MetricsReporter {
    return if (environment.sensuHost == "" || environment.sensuHost == "stub") {
        StubMetricsReporter()
    } else {
        val sensuConfig = createSensuConfig(environment)
        InfluxMetricsReporter(sensuConfig)
    }
}

private fun createSensuConfig(environment: Environment) = SensuConfig(
    hostName = environment.sensuHost,
    hostPort = environment.sensuPort,
    clusterName = environment.clusterName,
    namespace = "N/A",
    applicationName = "dittnav-event-aggregator",
    eventsTopLevelName = "aggregator-kafka-events",
    enableEventBatching = true
)
