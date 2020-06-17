package no.nav.personbruker.dittnav.metrics.total

import no.nav.personbruker.dittnav.metrics.reporting.*
import org.slf4j.LoggerFactory

class TotalEventsMetricsCollector (private val totalEventsRepository: TotalEventsRepository,
                                   private val measurementCollector: MeasurementCollector) {

    val log = LoggerFactory.getLogger(TotalEventsMetricsCollector::class.java)

    suspend fun getAndReportEventsPerUser() {
        tryFetch {
            totalEventsRepository.getEventsPerUser()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, EVENTS_PER_USER, MeasurementEventType.ANY, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall eventer per bruker. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportVisibleEventsPerUser() {
        tryFetch {
            totalEventsRepository.getVisibleEventsPerUser()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, VISIBLE_EVENTS_PER_USER, MeasurementEventType.ANY, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall synlige eventer per bruker. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportActiveEventsPerUser() {
        tryFetch {
            totalEventsRepository.getActiveEventsPerUser()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, ACTIVE_EVENTS_PER_USER, MeasurementEventType.ANY, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall aktive eventer per bruker. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportEventActiveRatePerUser() {
        tryFetch {
            totalEventsRepository.getEventActiveRatePerUser()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordDecimalMeasurement(measurement, EVENT_ACTIVE_RATE_PER_USER, MeasurementEventType.ANY, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for andel aktive er per bruker. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportExpiredEventsPerUser() {
        tryFetch {
            totalEventsRepository.getExpiredEventsPerUser()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, EXPIRED_EVENTS_PER_USER, MeasurementEventType.ANY, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall utgåtte eventer per bruker. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportEventExpiredRate() {
        tryFetch {
            totalEventsRepository.getEventExpiredRate()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordDecimalMeasurement(measurement, EVENT_EXPIRED_RATE_PER_USER, MeasurementEventType.ANY, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for andel utgåtte eventer per bruker. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportExpiredEventPerUserByInvisible() {
        tryFetch {
            totalEventsRepository.getExpiredEventPerUserByInvisible()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordDecimalMeasurement(measurement, EVENT_EXPIRED_RATE_PER_USER_BY_INVISIBLE, MeasurementEventType.ANY, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for utgåtte eventer som andel av skjulte eventer per bruker. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportEventsPerGroupId() {
        tryFetch {
            totalEventsRepository.getEventsPerGroupId()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, EVENTS_PER_GROUP_ID, MeasurementEventType.ANY, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall eventer per grupperingsid. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportGroupIdsPerUser() {
        tryFetch {
            totalEventsRepository.getGroupIdsPerUser()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, GROUP_IDS_PER_USER, MeasurementEventType.ANY, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall grupperingsid-er med minst ett -event per bruker. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportEventTextLength() {
        tryFetch {
            totalEventsRepository.getEventTextLength()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, EVENT_TEXT_LENGTH, MeasurementEventType.ANY, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for tekstlengde for eventer. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportNumberOfUsersWithEvents() {
        tryFetch {
            totalEventsRepository.getNumberOfUsersWithEvents()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordScalarIntMeasurement(measurement, USERS_WITH_EVENTS, MeasurementEventType.ANY, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall brukere med eventer. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportNumberOfEvents() {
        tryFetch {
            totalEventsRepository.getNumberOfEvents()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordScalarIntMeasurement(measurement, NUMBER_OF_EVENTS, MeasurementEventType.ANY, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall eventer. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportNumberOfVisibleEvents() {
        tryFetch {
            totalEventsRepository.getNumberOfVisibleEvents()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordScalarIntMeasurement(measurement, NUMBER_OF_VISIBLE_EVENTS, MeasurementEventType.ANY, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall synlige eventer. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportNumberOfActiveEvents() {
        tryFetch {
            totalEventsRepository.getNumberOfActiveEvents()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordScalarIntMeasurement(measurement, NUMBER_OF_ACTIVE_EVENTS, MeasurementEventType.ANY, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall aktive eventer. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportNumberOfExpiredEvents() {
        tryFetch {
            totalEventsRepository.getNumberOfExpiredEvents()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordScalarIntMeasurement(measurement, NUMBER_OF_EXPIRED_EVENTS, MeasurementEventType.ANY, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall utgåtte eventer. Tid brukt: ${processingTime}ms.")
        }
    } }