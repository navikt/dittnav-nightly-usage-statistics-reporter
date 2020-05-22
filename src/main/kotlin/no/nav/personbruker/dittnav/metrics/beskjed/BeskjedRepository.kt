package no.nav.personbruker.dittnav.metrics.beskjed

import no.nav.personbruker.dittnav.metrics.database.Database
import no.nav.personbruker.dittnav.metrics.database.entity.*

class BeskjedRepository(private val database: Database) {

    suspend fun getBeskjedEventsPerUser(): EventsPerUser {
        return database.dbQuery {
            measureBeskjedEventsPerUser()
        }
    }

    suspend fun getVisibleBeskjedEventsPerUser(): VisibleEventsPerUser {
        return database.dbQuery {
            measureVisibleBeskjedEventsPerUser()
        }
    }

    suspend fun getActiveBeskjedEventsPerUser(): ActiveEventsPerUser {
        return database.dbQuery {
            measureActiveBeskjedEventsPerUser()
        }
    }

    suspend fun getBeskjedEventActiveRate(): EventActiveRatePerUser {
        return database.dbQuery {
            measureBeskjedEventActiveRatePerUser()
        }
    }

    suspend fun getExpiredBeskjedEventsPerUser(): ExpiredEventsPerUser {
        return database.dbQuery {
            measureExpiredBeskjedEventsPerUser()
        }
    }

    suspend fun getBeskjedEventExpiredRate(): EventExpiredRatePerUser  {
        return database.dbQuery {
            measureBeskjedEventExpiredRatePerUser()
        }
    }

    suspend fun getExpiredBeskjedEventPerUserByInvisible(): EventExpiredRateByInvisiblePerUser {
        return database.dbQuery {
            measureBeskjedEventExpiredRatePerUserByInvisible()
        }
    }

    suspend fun getBeskjedEventsPerGroupId(): EventsPerGroupId {
        return database.dbQuery {
            measureBeskjedEventsPerGroupId()
        }
    }

    suspend fun getBeskjedGroupIdsPerUser(): GroupIdsPerUser {
        return database.dbQuery {
            measureBeskjedGroupIdsPerUser()
        }
    }

    suspend fun getNumberOfUsersWithBeskjedEvents(): Int {
        return database.dbQuery {
            countUsersWithBeskjedEvents()
        }
    }

    suspend fun getNumberOfBeskjedEvents(): Int {
        return database.dbQuery {
            countNumberOfBeskjedEvents()
        }
    }

    suspend fun getNumberOfVisibleBeskjedEvents(): Int {
        return database.dbQuery {
            countNumberOfVisibleBeskjedEvents()
        }
    }

    suspend fun getNumberOfActiveBeskjedEvents(): Int {
        return database.dbQuery {
            countNumberOfActiveBeskjedEvents()
        }
    }

    suspend fun getNumberOfExpiredBeskjedEvents(): Int {
        return database.dbQuery {
            countNumberOfExpiredBeskjedEvents()
        }
    }

}