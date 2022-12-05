package no.nav.personbruker.dittnav.metrics.collectors

import no.nav.personbruker.dittnav.metrics.common.StatisticsService
import no.nav.personbruker.dittnav.metrics.common.EventType
import no.nav.personbruker.dittnav.metrics.reporting.*
import no.nav.personbruker.dittnav.metrics.reporting.MeasurementEventType.BESKJED
import org.slf4j.LoggerFactory

class BeskjedMetricsCollector(private val statisticsService: StatisticsService,
                              private val measurementCollector: MeasurementCollector) {

    val log = LoggerFactory.getLogger(BeskjedMetricsCollector::class.java)

    suspend fun getAndReportBeskjedEventsPerUser() {
        tryFetch {
            statisticsService.getEventsPerUser(EventType.BESKJED)
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, EVENTS_PER_USER, BESKJED, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall beskjed-eventer per bruker. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportActiveBeskjedEventsPerUser() {
        tryFetch {
            statisticsService.getActiveEventsPerUser(EventType.BESKJED)
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, ACTIVE_EVENTS_PER_USER, BESKJED, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall aktive beskjed-eventer per bruker. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportBeskjedEventTextLength() {
        tryFetch {
            statisticsService.getEventTextLength(EventType.BESKJED)
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, EVENT_TEXT_LENGTH, BESKJED, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for tekstlengde for beskjed-eventer. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportNumberOfUsersWithBeskjedEvents() {
        tryFetch {
            statisticsService.getNumberOfUsersWithEvents(EventType.BESKJED)
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordScalarIntMeasurement(measurement, USERS_WITH_EVENTS, BESKJED, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall brukere med beskjed-eventer. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportNumberOfBeskjedEvents() {
        tryFetch {
            statisticsService.getNumberOfEvents(EventType.BESKJED)
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordScalarIntMeasurement(measurement, NUMBER_OF_EVENTS, BESKJED, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall beskjed-eventer. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportActiveBeskjedFrequencies() {
        tryFetch {
            statisticsService.getActiveEventsFrequencyDistribution(EventType.BESKJED)
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordEventFrequencyMeasurement(measurement, EVENT_FREQUENCY_DISTRIBUTION, BESKJED, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for aktiv beskjed-frekvensfordeling. Tid brukt: ${processingTime}ms.")
        }
    }
}
