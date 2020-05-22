package no.nav.personbruker.dittnav.metrics.reporting

interface MetricsReporter {
    suspend fun registerDataPoint(measurement: String, fields: Map<String, Any>, tags: Map<String, String>)
}