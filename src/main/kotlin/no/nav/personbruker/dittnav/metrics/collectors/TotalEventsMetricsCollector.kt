package no.nav.personbruker.dittnav.metrics.collectors

import no.nav.personbruker.dittnav.metrics.common.StatisticsService
import no.nav.personbruker.dittnav.metrics.reporting.*
import org.slf4j.LoggerFactory

class TotalEventsMetricsCollector(private val statisticsService: StatisticsService,
                                  private val measurementCollector: MeasurementCollector) {

    val log = LoggerFactory.getLogger(TotalEventsMetricsCollector::class.java)

    suspend fun getAndReportEventsPerUser() {
        tryFetch {
            statisticsService.getTotalEventsPerUser()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, EVENTS_PER_USER, MeasurementEventType.ANY, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall eventer per bruker. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportActiveEventsPerUser() {
        tryFetch {
            statisticsService.getTotalActiveEventsPerUser()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, ACTIVE_EVENTS_PER_USER, MeasurementEventType.ANY, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall aktive eventer per bruker. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportEventTextLength() {
        tryFetch {
            statisticsService.getTotalEventTextLength()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, EVENT_TEXT_LENGTH, MeasurementEventType.ANY, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for tekstlengde for eventer. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportNumberOfUsersWithEvents() {
        tryFetch {
            statisticsService.getTotalNumberOfUsersWithEvents()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordScalarIntMeasurement(measurement, USERS_WITH_EVENTS, MeasurementEventType.ANY, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall brukere med eventer. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportNumberOfEvents() {
        tryFetch {
            statisticsService.getTotalNumberOfEvents()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordScalarIntMeasurement(measurement, NUMBER_OF_EVENTS, MeasurementEventType.ANY, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall eventer. Tid brukt: ${processingTime}ms.")
        }
    }
    
}
