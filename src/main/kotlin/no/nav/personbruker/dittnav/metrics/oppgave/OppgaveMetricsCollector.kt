package no.nav.personbruker.dittnav.metrics.oppgave

import no.nav.personbruker.dittnav.metrics.reporting.*
import no.nav.personbruker.dittnav.metrics.reporting.MeasurementEventType.OPPGAVE
import org.slf4j.LoggerFactory

class OppgaveMetricsCollector(private val oppgaveRepository: OppgaveRepository,
                              private val measurementCollector: MeasurementCollector) {

    val log = LoggerFactory.getLogger(OppgaveMetricsCollector::class.java)

    suspend fun getAndReportOppgaveEventsPerUser() {
        tryFetch {
            oppgaveRepository.getOppgaveEventsPerUser()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, EVENTS_PER_USER, OPPGAVE, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall oppgave-eventer per bruker. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportVisibleOppgaveEventsPerUser() {
        tryFetch {
            oppgaveRepository.getVisibleOppgaveEventsPerUser()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, VISIBLE_EVENTS_PER_USER, OPPGAVE, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall synlige oppgave-eventer per bruker. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportActiveOppgaveEventsPerUser() {
        tryFetch {
            oppgaveRepository.getActiveOppgaveEventsPerUser()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, ACTIVE_EVENTS_PER_USER, OPPGAVE, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall aktive oppgave-eventer per bruker. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportOppgaveEventsPerGroupId() {
        tryFetch {
            oppgaveRepository.getOppgaveEventsPerGroupId()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, EVENTS_PER_GROUP_ID, OPPGAVE, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall oppgave-eventer per grupperingsid. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportOppgaveGroupIdsPerUser() {
        tryFetch {
            oppgaveRepository.getOppgaveGroupIdsPerUser()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, GROUP_IDS_PER_USER, OPPGAVE, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall grupperingsid-er med minst ett oppgave-event per bruker. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportNumberOfUsersWithOppgaveEvents() {
        tryFetch {
            oppgaveRepository.getNumberOfUsersWithOppgaveEvents()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordScalarIntMeasurement(measurement, USERS_WITH_EVENTS, OPPGAVE, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall brukere med oppgave-eventer. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportNumberOfOppgaveEvents() {
        tryFetch {
            oppgaveRepository.getNumberOfOppgaveEvents()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordScalarIntMeasurement(measurement, NUMBER_OF_EVENTS, OPPGAVE, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall oppgave-eventer. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportNumberOfVisibleOppgaveEvents() {
        tryFetch {
            oppgaveRepository.getNumberOfVisibleOppgaveEvents()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordScalarIntMeasurement(measurement, NUMBER_OF_VISIBLE_EVENTS, OPPGAVE, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall synlige oppgave-eventer. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportNumberOfActiveOppgaveEvents() {
        tryFetch {
            oppgaveRepository.getNumberOfActiveOppgaveEvents()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordScalarIntMeasurement(measurement, NUMBER_OF_ACTIVE_EVENTS, OPPGAVE, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall aktive oppgave-eventer. Tid brukt: ${processingTime}ms.")
        }
    }

}