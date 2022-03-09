package no.nav.personbruker.dittnav.metrics.collectors

import no.nav.personbruker.dittnav.metrics.common.StatisticsService
import no.nav.personbruker.dittnav.metrics.common.EventType
import no.nav.personbruker.dittnav.metrics.reporting.MeasurementCollector
import no.nav.personbruker.dittnav.metrics.reporting.MeasurementEventType.DONE
import no.nav.personbruker.dittnav.metrics.reporting.NUMBER_OF_CACHED_DONE_EVENTS
import no.nav.personbruker.dittnav.metrics.reporting.tryFetch
import org.slf4j.LoggerFactory

class DoneMetricsCollector(private val statisticsService: StatisticsService,
                           private val measurementCollector: MeasurementCollector) {

    private val log = LoggerFactory.getLogger(DoneMetricsCollector::class.java)

    suspend fun getAndReportNumberOfCachedDoneEvents() {
        tryFetch {
            statisticsService.getNumberOfEvents(EventType.DONE)
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordScalarIntMeasurement(measurement, NUMBER_OF_CACHED_DONE_EVENTS, DONE, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall done-eventer per bruker. Tid brukt: ${processingTime}ms.")
        }
    }

}
