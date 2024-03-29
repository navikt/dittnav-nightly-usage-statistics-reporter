package no.nav.personbruker.dittnav.metrics.collectors

import no.nav.personbruker.dittnav.metrics.common.StatisticsService
import no.nav.personbruker.dittnav.metrics.common.EventType
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

    suspend fun getAndReportActiveInnboksFrequencies() {
        tryFetch {
            statisticsService.getActiveEventsFrequencyDistribution(EventType.INNBOKS)
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordEventFrequencyMeasurement(measurement, EVENT_FREQUENCY_DISTRIBUTION,
                INNBOKS, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for aktiv innboks-frekvensfordeling. Tid brukt: ${processingTime}ms.")
        }
    }
}
