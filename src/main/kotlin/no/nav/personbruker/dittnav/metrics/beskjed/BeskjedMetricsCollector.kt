package no.nav.personbruker.dittnav.metrics.beskjed

import no.nav.personbruker.dittnav.metrics.common.StatisticsService
import no.nav.personbruker.dittnav.metrics.config.EventType
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

    suspend fun getAndReportBeskjedEventActiveRate() {
        tryFetch {
            statisticsService.getEventActiveRate(EventType.BESKJED)
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordDecimalMeasurement(measurement, EVENT_ACTIVE_RATE_PER_USER, BESKJED, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for andel aktive beskjeder per bruker. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportBeskjedEventsPerGroupId() {
        tryFetch {
            statisticsService.getEventsPerGroupId(EventType.BESKJED)
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, EVENTS_PER_GROUP_ID, BESKJED, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall beskjed-eventer per grupperingsid. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportBeskjedGroupIdsPerUser() {
        tryFetch {
            statisticsService.getGroupIdsPerUser(EventType.BESKJED)
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, GROUP_IDS_PER_USER, BESKJED, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall grupperingsid-er med minst ett beskjed-event per bruker. Tid brukt: ${processingTime}ms.")
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

    suspend fun getAndReportNumberOfActiveBeskjedEvents() {
        tryFetch {
            statisticsService.getNumberOfActiveEvents(EventType.BESKJED)
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordScalarIntMeasurement(measurement, NUMBER_OF_ACTIVE_EVENTS, BESKJED, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall aktive beskjed-eventer. Tid brukt: ${processingTime}ms.")
        }
    }
}
