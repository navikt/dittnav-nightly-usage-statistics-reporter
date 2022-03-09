package no.nav.personbruker.dittnav.metrics.innboks

import no.nav.personbruker.dittnav.metrics.common.StatisticsService
import no.nav.personbruker.dittnav.metrics.config.EventType
import no.nav.personbruker.dittnav.metrics.reporting.*
import no.nav.personbruker.dittnav.metrics.reporting.MeasurementEventType.INNBOKS
import org.slf4j.LoggerFactory

class InnboksMetricsCollector(private val statisticsService: StatisticsService,
                              private val measurementCollector: MeasurementCollector) {

    val log = LoggerFactory.getLogger(InnboksMetricsCollector::class.java)

    suspend fun getAndReportInnboksEventsPerUser() {
        tryFetch {
            statisticsService.getEventsPerUser(EventType.INNBOKS)
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, EVENTS_PER_USER, INNBOKS, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall innboks-eventer per bruker. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportActiveInnboksEventsPerUser() {
        tryFetch {
            statisticsService.getActiveEventsPerUser(EventType.INNBOKS)
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, ACTIVE_EVENTS_PER_USER, INNBOKS, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall aktive innboks-eventer per bruker. Tid brukt: ${processingTime}ms.")
        }
    }
    
    suspend fun getAndReportInnboksEventActiveRatePerUser() {
        tryFetch {
            statisticsService.getEventActiveRate(EventType.INNBOKS)
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordDecimalMeasurement(measurement, EVENT_ACTIVE_RATE_PER_USER, INNBOKS, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for andel aktive innboks-eventer per bruker. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportInnboksEventsPerGroupId() {
        tryFetch {
            statisticsService.getEventsPerGroupId(EventType.INNBOKS)
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, EVENTS_PER_GROUP_ID, INNBOKS, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall innboks-eventer per grupperingsid. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportInnboksGroupIdsPerUser() {
        tryFetch {
            statisticsService.getGroupIdsPerUser(EventType.INNBOKS)
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, GROUP_IDS_PER_USER, INNBOKS, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall grupperingsid-er med minst ett innboks-event per bruker. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportInnboksEventTextLength() {
        tryFetch {
            statisticsService.getEventTextLength(EventType.INNBOKS)
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, EVENT_TEXT_LENGTH, INNBOKS, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for tekstlengde for innboks-eventer. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportNumberOfUsersWithInnboksEvents() {
        tryFetch {
            statisticsService.getNumberOfUsersWithEvents(EventType.INNBOKS)
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordScalarIntMeasurement(measurement, USERS_WITH_EVENTS, INNBOKS, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall brukere med innboks-eventer. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportNumberOfInnboksEvents() {
        tryFetch {
            statisticsService.getNumberOfEvents(EventType.INNBOKS)
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordScalarIntMeasurement(measurement, NUMBER_OF_EVENTS, INNBOKS, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall innboks-eventer. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportNumberOfActiveInnboksEvents() {
        tryFetch {
            statisticsService.getNumberOfActiveEvents(EventType.INNBOKS)
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordScalarIntMeasurement(measurement, NUMBER_OF_ACTIVE_EVENTS, INNBOKS, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall aktive innboks-eventer. Tid brukt: ${processingTime}ms.")
        }
    }
}
