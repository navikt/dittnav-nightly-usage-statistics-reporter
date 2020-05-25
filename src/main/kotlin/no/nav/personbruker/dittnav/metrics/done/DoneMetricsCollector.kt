package no.nav.personbruker.dittnav.metrics.done

import jdk.nashorn.internal.runtime.regexp.joni.Config.log
import no.nav.personbruker.dittnav.metrics.reporting.MeasurementCollector
import no.nav.personbruker.dittnav.metrics.reporting.MeasurementEventType
import no.nav.personbruker.dittnav.metrics.reporting.MeasurementEventType.DONE
import no.nav.personbruker.dittnav.metrics.reporting.NUMBER_OF_CACHED_DONE_EVENTS
import no.nav.personbruker.dittnav.metrics.reporting.tryFetch
import org.slf4j.LoggerFactory

class DoneMetricsCollector(private val doneRepository: DoneRepository,
                           private val measurementCollector: MeasurementCollector) {

    private val log = LoggerFactory.getLogger(DoneMetricsCollector::class.java)

    suspend fun getAndReportNumberOfCachedDoneEvents() {
        tryFetch {
            doneRepository.getNumberOfCachedDoneEvents()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordScalarIntMeasurement(measurement, NUMBER_OF_CACHED_DONE_EVENTS, DONE, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall innboks-eventer per bruker. Tid brukt: ${processingTime}ms.")
        }
    }
}