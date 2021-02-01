package no.nav.personbruker.dittnav.metrics.reporting

import no.nav.personbruker.dittnav.common.metrics.MetricsReporter
import no.nav.personbruker.dittnav.metrics.database.entity.DecimalMeasurement
import no.nav.personbruker.dittnav.metrics.database.entity.IntegerMeasurement
import org.slf4j.LoggerFactory

class MeasurementCollector(private val metricsReporter: MetricsReporter) {

    private val log = LoggerFactory.getLogger(MeasurementCollector::class.java)

    private val REPORTING_ATTEMPTS = 3

    suspend fun recordIntegerMeasurement(measurement: IntegerMeasurement, name: String, type: MeasurementEventType, processingTime: Long) {
        val fieldMap = listOf(
            "min" to measurement.min,
            "max" to measurement.max,
            "avg" to measurement.avg,
            "25th_percentile" to measurement.percentile25,
            "50th_percentile" to measurement.percentile50,
            "75th_percentile" to measurement.percentile75,
            "90th_percentile" to measurement.percentile90,
            "99th_percentile" to measurement.percentile99,
            "processingTimeMs" to processingTime
        ).toMap()

        val tagMap = listOf(
            "type" to type.name
        ).toMap()

        registerDataPoint(name, fieldMap, tagMap)
    }

    suspend fun recordDecimalMeasurement(measurement: DecimalMeasurement, name: String, type: MeasurementEventType, processingTime: Long) {
        val fieldMap = listOf(
            "min" to measurement.min,
            "max" to measurement.max,
            "avg" to measurement.avg,
            "25th_percentile" to measurement.percentile25,
            "50th_percentile" to measurement.percentile50,
            "75th_percentile" to measurement.percentile75,
            "90th_percentile" to measurement.percentile90,
            "99th_percentile" to measurement.percentile99,
            "processingTimeMs" to processingTime
        ).toMap()

        val tagMap = listOf(
            "type" to type.name
        ).toMap()

        registerDataPoint(name, fieldMap, tagMap)
    }

    suspend fun recordScalarIntMeasurement(measurement: Int, name: String, type: MeasurementEventType, processingTime: Long) {
        val fieldMap = listOf(
            "counter" to measurement,
            "processingTimeMs" to processingTime
        ).toMap()

        val tagMap = listOf(
            "type" to type.name
        ).toMap()


        registerDataPoint(name, fieldMap, tagMap)
    }

    private suspend fun registerDataPoint(measurement: String, fields: Map<String, Any>, tags: Map<String, String>) {
        try {
            metricsReporter.registerDataPoint(measurement, fields, tags)
        } catch (e: Exception) {
            log.warn("Klarte ikke rapportere metrikk {$measurement, ${tags["type"]}}. Hopper over.")
        }
    }
}
