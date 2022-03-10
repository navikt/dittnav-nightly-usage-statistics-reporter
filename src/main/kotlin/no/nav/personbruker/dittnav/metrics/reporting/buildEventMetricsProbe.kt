package no.nav.personbruker.dittnav.metrics.reporting

import no.nav.personbruker.dittnav.common.metrics.MetricsReporter
import no.nav.personbruker.dittnav.common.metrics.StubMetricsReporter
import no.nav.personbruker.dittnav.common.metrics.influxdb.InfluxConfig
import no.nav.personbruker.dittnav.common.metrics.influxdb.InfluxMetricsReporter
import no.nav.personbruker.dittnav.metrics.config.Environment

fun buildMetricsReporter(environment: Environment): MetricsReporter {
    return if (environment.influxdbHost == "" || environment.influxdbHost == "stub") {
        StubMetricsReporter()
    } else {
        val influxConfig = createInfluxConfig(environment)
        InfluxMetricsReporter(influxConfig)
    }
}

private fun createInfluxConfig(environment: Environment) = InfluxConfig(
    applicationName = environment.appnavn,
    hostName = environment.influxdbHost,
    hostPort = environment.influxdbPort,
    databaseName = environment.influxdbName,
    retentionPolicyName = environment.influxdbRetentionPolicy,
    clusterName = environment.clusterName,
    namespace = environment.namespace,
    userName = environment.influxdbUser,
    password = environment.influxdbPassword,
    enableEventBatching = false
)
