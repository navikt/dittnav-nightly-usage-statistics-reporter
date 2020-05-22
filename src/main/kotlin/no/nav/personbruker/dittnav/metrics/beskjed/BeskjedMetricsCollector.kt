package no.nav.personbruker.dittnav.metrics.beskjed

import no.nav.personbruker.dittnav.metrics.reporting.EVENTS_PER_USER
import no.nav.personbruker.dittnav.metrics.reporting.MeasurementCollector
import no.nav.personbruker.dittnav.metrics.reporting.MeasurementEventType
import no.nav.personbruker.dittnav.metrics.reporting.tryFetch

class BeskjedMetricsCollector(private val beskjedRepository: BeskjedRepository,
                              private val measurementCollector: MeasurementCollector) {

    suspend fun getAndReportBeskjedEventsPerUser() {

        val startTime = System.currentTimeMillis()

        tryFetch {
            beskjedRepository.getBeskjedEventsPerUser()
        }.onSuccess { measurement ->
            measurementCollector.recordIntegerMeasurement(measurement, EVENTS_PER_USER, MeasurementEventType.BESKJED, startTime)
        }
    }
}