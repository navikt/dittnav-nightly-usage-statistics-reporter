package no.nav.personbruker.dittnav.metrics.total

import no.nav.personbruker.dittnav.metrics.database.Database
import no.nav.personbruker.dittnav.metrics.database.entity.*

class TotalEventsRepository(private val database: Database) {

    suspend fun getEventsPerUser(): EventsPerUser {
        return database.dbQuery {
            measureEventsPerUser()
        }
    }

    suspend fun getVisibleEventsPerUser(): VisibleEventsPerUser {
        return database.dbQuery {
            measureVisibleEventsPerUser()
        }
    }

    suspend fun getActiveEventsPerUser(): ActiveEventsPerUser {
        return database.dbQuery {
            measureActiveEventsPerUser()
        }
    }

    suspend fun getEventActiveRatePerUser(): EventActiveRatePerUser {
        return database.dbQuery {
            measureEventActiveRatePerUser()
        }
    }

    suspend fun getExpiredEventsPerUser(): ExpiredEventsPerUser {
        return database.dbQuery {
            measureExpiredEventsPerUser()
        }
    }

    suspend fun getEventExpiredRate(): EventExpiredRatePerUser  {
        return database.dbQuery {
            measureEventExpiredRatePerUser()
        }
    }

    suspend fun getExpiredEventPerUserByInvisible(): EventExpiredRateByInvisiblePerUser {
        return database.dbQuery {
            measureEventExpiredRatePerUserByInvisible()
        }
    }

    suspend fun getEventsPerGroupId(): EventsPerGroupId {
        return database.dbQuery {
            measureEventsPerGroupId()
        }
    }

    suspend fun getGroupIdsPerUser(): GroupIdsPerUser {
        return database.dbQuery {
            measureGroupIdsPerUser()
        }
    }

    suspend fun getEventTextLength(): EventTextLength {
        return database.dbQuery {
            measureEventTextLength()
        }
    }

    suspend fun getNumberOfUsersWithEvents(): Int {
        return database.dbQuery {
            countUsersWithEvents()
        }
    }

    suspend fun getNumberOfEvents(): Int {
        return database.dbQuery {
            countNumberOfEvents()
        }
    }

    suspend fun getNumberOfVisibleEvents(): Int {
        return database.dbQuery {
            countNumberOfVisibleEvents()
        }
    }

    suspend fun getNumberOfActiveEvents(): Int {
        return database.dbQuery {
            countNumberOfActiveEvents()
        }
    }

    suspend fun getNumberOfExpiredEvents(): Int {
        return database.dbQuery {
            countNumberOfExpiredEvents()
        }
    }
}