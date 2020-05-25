package no.nav.personbruker.dittnav.metrics.beskjed

import no.nav.personbruker.dittnav.metrics.reporting.*
import no.nav.personbruker.dittnav.metrics.reporting.MeasurementEventType.BESKJED
import org.slf4j.LoggerFactory

class BeskjedMetricsCollector(private val beskjedRepository: BeskjedRepository,
                              private val measurementCollector: MeasurementCollector) {

    val log = LoggerFactory.getLogger(BeskjedMetricsCollector::class.java)

    suspend fun getAndReportBeskjedEventsPerUser() {
        tryFetch {
            beskjedRepository.getBeskjedEventsPerUser()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, EVENTS_PER_USER, BESKJED, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall beskjed-eventer per bruker. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportVisibleBeskjedEventsPerUser() {
        tryFetch {
            beskjedRepository.getVisibleBeskjedEventsPerUser()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, VISIBLE_EVENTS_PER_USER, BESKJED, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall synlige beskjed-eventer per bruker. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportActiveBeskjedEventsPerUser() {
        tryFetch {
            beskjedRepository.getActiveBeskjedEventsPerUser()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, ACTIVE_EVENTS_PER_USER, BESKJED, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall aktive beskjed-eventer per bruker. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportBeskjedEventActiveRate() {
        tryFetch {
            beskjedRepository.getBeskjedEventActiveRate()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordDecimalMeasurement(measurement, EVENT_ACTIVE_RATE_PER_USER, BESKJED, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for andel aktive beskjeder per bruker. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportExpiredBeskjedEventsPerUser() {
        tryFetch {
            beskjedRepository.getExpiredBeskjedEventsPerUser()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, EXPIRED_EVENTS_PER_USER, BESKJED, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall utgåtte beskjed-eventer per bruker. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportBeskjedEventExpiredRate() {
        tryFetch {
            beskjedRepository.getBeskjedEventExpiredRate()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordDecimalMeasurement(measurement, EVENT_EXPIRED_RATE_PER_USER, BESKJED, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for andel utgåtte beskjed-eventer per bruker. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportExpiredBeskjedEventPerUserByInvisible() {
        tryFetch {
            beskjedRepository.getExpiredBeskjedEventPerUserByInvisible()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordDecimalMeasurement(measurement, EVENT_EXPIRED_RATE_PER_USER_BY_INVISIBLE, BESKJED, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for utgåtte beskjed-eventer som andel av skjulte eventer per bruker. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportBeskjedEventsPerGroupId() {
        tryFetch {
            beskjedRepository.getBeskjedEventsPerGroupId()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, EVENTS_PER_GROUP_ID, BESKJED, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall beskjed-eventer per grupperingsid. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportBeskjedGroupIdsPerUser() {
        tryFetch {
            beskjedRepository.getBeskjedGroupIdsPerUser()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordIntegerMeasurement(measurement, GROUP_IDS_PER_USER, BESKJED, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall grupperingsid-er med minst ett beskjed-event per bruker. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportNumberOfUsersWithBeskjedEvents() {
        tryFetch {
            beskjedRepository.getNumberOfUsersWithBeskjedEvents()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordScalarIntMeasurement(measurement, USERS_WITH_EVENTS, BESKJED, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall brukere med beskjed-eventer. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportNumberOfBeskjedEvents() {
        tryFetch {
            beskjedRepository.getNumberOfBeskjedEvents()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordScalarIntMeasurement(measurement, NUMBER_OF_EVENTS, BESKJED, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall beskjed-eventer. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportNumberOfVisibleBeskjedEvents() {
        tryFetch {
            beskjedRepository.getNumberOfVisibleBeskjedEvents()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordScalarIntMeasurement(measurement, NUMBER_OF_VISIBLE_EVENTS, BESKJED, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall synlige beskjed-eventer. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportNumberOfActiveBeskjedEvents() {
        tryFetch {
            beskjedRepository.getNumberOfActiveBeskjedEvents()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordScalarIntMeasurement(measurement, NUMBER_OF_ACTIVE_EVENTS, BESKJED, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall aktive beskjed-eventer. Tid brukt: ${processingTime}ms.")
        }
    }

    suspend fun getAndReportNumberOfExpiredBeskjedEvents() {
        tryFetch {
            beskjedRepository.getNumberOfExpiredBeskjedEvents()
        }.onSuccess { measurement, processingTime ->
            measurementCollector.recordScalarIntMeasurement(measurement, NUMBER_OF_EXPIRED_EVENTS, BESKJED, processingTime)
        }.onFailure { processingTime ->
            log.warn("Klarte ikke hente inn data for antall utgåtte beskjed-eventer. Tid brukt: ${processingTime}ms.")
        }
    }

}