package no.nav.personbruker.dittnav.metrics.reporting

import no.nav.personbruker.dittnav.metrics.config.Environment
import no.nav.personbruker.dittnav.metrics.reporting.influx.InfluxMetricsReporter
import no.nav.personbruker.dittnav.metrics.reporting.influx.SensuClient

private fun resolveMetricsReporter(environment: Environment): MetricsReporter {
    return if (environment.sensuHost == "" || environment.sensuHost == "stub") {
        StubMetricsReporter()
    } else {
        val sensuClient = SensuClient(environment.sensuHost, environment.sensuPort.toInt())
        InfluxMetricsReporter(sensuClient, environment)
    }
}