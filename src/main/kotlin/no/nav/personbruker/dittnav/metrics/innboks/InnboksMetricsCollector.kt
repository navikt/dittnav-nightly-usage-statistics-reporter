package no.nav.personbruker.dittnav.metrics.innboks

import no.nav.personbruker.dittnav.metrics.reporting.*
import no.nav.personbruker.dittnav.metrics.reporting.MeasurementEventType.INNBOKS
import org.slf4j.LoggerFactory

class InnboksMetricsCollector(private val innboksRepository: InnboksRepository,
                              private val measurementCollector: MeasurementCollector) {

    val log = LoggerFactory.getLogger(InnboksMetricsCollector::class.java)

    suspend fun getAndReportInnboksEventsPerUser() {
        tryFetch {
            innboksRepository.getInnboksEventsPerUser()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, EVENTS_PER_USER, INNBOKS, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall innboks-eventer per bruker. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportVisibleInnboksEventsPerUser() {
        tryFetch {
            innboksRepository.getVisibleInnboksEventsPerUser()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, VISIBLE_EVENTS_PER_USER, INNBOKS, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall synlige innboks-eventer per bruker. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportActiveInnboksEventsPerUser() {
        tryFetch {
            innboksRepository.getActiveInnboksEventsPerUser()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, ACTIVE_EVENTS_PER_USER, INNBOKS, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall aktive innboks-eventer per bruker. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportInnboksEventsPerGroupId() {
        tryFetch {
            innboksRepository.getInnboksEventsPerGroupId()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, EVENTS_PER_GROUP_ID, INNBOKS, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall innboks-eventer per grupperingsid. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportInnboksGroupIdsPerUser() {
        tryFetch {
            innboksRepository.getInnboksGroupIdsPerUser()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, GROUP_IDS_PER_USER, INNBOKS, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall grupperingsid-er med minst ett innboks-event per bruker. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportNumberOfUsersWithInnboksEvents() {
        tryFetch {
            innboksRepository.getNumberOfUsersWithInnboksEvents()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordScalarIntMeasurement(measurement, USERS_WITH_EVENTS, INNBOKS, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall brukere med innboks-eventer. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportNumberOfInnboksEvents() {
        tryFetch {
            innboksRepository.getNumberOfInnboksEvents()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordScalarIntMeasurement(measurement, NUMBER_OF_EVENTS, INNBOKS, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall innboks-eventer. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportNumberOfVisibleInnboksEvents() {
        tryFetch {
            innboksRepository.getNumberOfVisibleInnboksEvents()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordScalarIntMeasurement(measurement, NUMBER_OF_VISIBLE_EVENTS, INNBOKS, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall synlige innboks-eventer. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportNumberOfActiveInnboksEvents() {
        tryFetch {
            innboksRepository.getNumberOfActiveInnboksEvents()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordScalarIntMeasurement(measurement, NUMBER_OF_ACTIVE_EVENTS, INNBOKS, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall aktive innboks-eventer. Tid brukt: ${processingTime}ms.")
        }
    }

}