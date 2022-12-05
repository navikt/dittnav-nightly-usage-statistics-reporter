package no.nav.personbruker.dittnav.metrics.collectors

import no.nav.personbruker.dittnav.metrics.common.StatisticsService
import no.nav.personbruker.dittnav.metrics.common.EventType
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

    suspend fun getAndReportActiveOppgaveFrequencies() {
        tryFetch {
            statisticsService.getActiveEventsFrequencyDistribution(EventType.OPPGAVE)
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordEventFrequencyMeasurement(measurement, EVENT_FREQUENCY_DISTRIBUTION,
                OPPGAVE, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for aktiv oppgave-frekvensfordeling. Tid brukt: ${processingTime}ms.")
        }
    }
}
