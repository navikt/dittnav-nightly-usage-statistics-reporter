package no.nav.personbruker.dittnav.metrics.beskjed

import no.nav.personbruker.dittnav.metrics.database.Database
import no.nav.personbruker.dittnav.metrics.database.entity.*

class BeskjedRepository(private val database: Database) {

    suspend fun getActiveBeskjedEventsPerUser(): ActiveEventsPerUser {
        return database.dbQuery {
            measureActiveBeskjedEventsPerUser()
        }
    }

    suspend fun getBeskjedEventActiveRate(): EventActiveRate {
        return database.dbQuery {
            measureBeskjedEventActiveRate()
        }
    }

    suspend fun getBeskjedEventsPerGroupId(): EventsPerGroupId {
        return database.dbQuery {
            measureBeskjedEventsPerGroupId()
        }
    }

    suspend fun getBeskjedEventsPerUser(): EventsPerUser {
        return database.dbQuery {
            measureBeskjedEventsPerUser()
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
}