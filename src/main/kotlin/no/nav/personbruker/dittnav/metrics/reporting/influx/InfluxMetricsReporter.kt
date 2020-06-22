package no.nav.personbruker.dittnav.metrics.reporting.influx

import no.nav.personbruker.dittnav.metrics.config.Environment
import no.nav.personbruker.dittnav.metrics.reporting.MetricsReporter
import org.influxdb.dto.Point
import org.slf4j.LoggerFactory
import java.util.concurrent.TimeUnit

class InfluxMetricsReporter(val sensuClient: SensuClient, val environment: Environment) : MetricsReporter {

    override suspend fun registerDataPoint(measurement: String, fields: Map<String, Any>, tags: Map<String, String>) {
        val point = Point.measurement(measurement)
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag(tags)
                .tag(DEFAULT_TAGS)
                .fields(fields)
                .build()

        sensuClient.submitEvent(SensuEvent(point))
    }

    private val DEFAULT_TAGS = listOf(
        "application" to "dittnav-event-aggregator",
        "cluster" to environment.clusterName
    ).toMap()
}