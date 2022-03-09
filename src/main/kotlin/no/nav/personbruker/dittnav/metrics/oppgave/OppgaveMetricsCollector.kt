package no.nav.personbruker.dittnav.metrics.oppgave

import no.nav.personbruker.dittnav.metrics.common.StatisticsService
import no.nav.personbruker.dittnav.metrics.config.EventType
import no.nav.personbruker.dittnav.metrics.reporting.*
import no.nav.personbruker.dittnav.metrics.reporting.MeasurementEventType.OPPGAVE
import org.slf4j.LoggerFactory

class OppgaveMetricsCollector(private val statisticsService: StatisticsService,
                              private val measurementCollector: MeasurementCollector) {

    val log = LoggerFactory.getLogger(OppgaveMetricsCollector::class.java)

    suspend fun getAndReportOppgaveEventsPerUser() {
        tryFetch {
            statisticsService.getEventsPerUser(EventType.OPPGAVE)
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, EVENTS_PER_USER, OPPGAVE, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall oppgave-eventer per bruker. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportActiveOppgaveEventsPerUser() {
        tryFetch {
            statisticsService.getActiveEventsPerUser(EventType.OPPGAVE)
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, ACTIVE_EVENTS_PER_USER, OPPGAVE, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall aktive oppgave-eventer per bruker. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportOppgaveEventActiveRatePerUser() {
        tryFetch {
            statisticsService.getEventActiveRate(EventType.OPPGAVE)
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordDecimalMeasurement(measurement, EVENT_ACTIVE_RATE_PER_USER, OPPGAVE, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for andel aktive oppgave-eventer per bruker. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportOppgaveEventsPerGroupId() {
        tryFetch {
            statisticsService.getEventsPerGroupId(EventType.OPPGAVE)
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, EVENTS_PER_GROUP_ID, OPPGAVE, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall oppgave-eventer per grupperingsid. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportOppgaveGroupIdsPerUser() {
        tryFetch {
            statisticsService.getGroupIdsPerUser(EventType.OPPGAVE)
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, GROUP_IDS_PER_USER, OPPGAVE, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall grupperingsid-er med minst ett oppgave-event per bruker. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportOppgaveEventTextLength() {
        tryFetch {
            statisticsService.getEventTextLength(EventType.OPPGAVE)
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, EVENT_TEXT_LENGTH, OPPGAVE, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for tekstlengde for oppgave-eventer. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportNumberOfUsersWithOppgaveEvents() {
        tryFetch {
            statisticsService.getNumberOfUsersWithEvents(EventType.OPPGAVE)
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordScalarIntMeasurement(measurement, USERS_WITH_EVENTS, OPPGAVE, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall brukere med oppgave-eventer. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportNumberOfOppgaveEvents() {
        tryFetch {
            statisticsService.getNumberOfEvents(EventType.OPPGAVE)
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordScalarIntMeasurement(measurement, NUMBER_OF_EVENTS, OPPGAVE, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall oppgave-eventer. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportNumberOfActiveOppgaveEvents() {
        tryFetch {
            statisticsService.getNumberOfActiveEvents(EventType.OPPGAVE)
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordScalarIntMeasurement(measurement, NUMBER_OF_ACTIVE_EVENTS, OPPGAVE, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall aktive oppgave-eventer. Tid brukt: ${processingTime}ms.")
        }
    }
}
