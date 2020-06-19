package no.nav.personbruker.dittnav.metrics.innboks

import no.nav.personbruker.dittnav.metrics.database.Database
import no.nav.personbruker.dittnav.metrics.database.entity.*

class InnboksRepository(private val database: Database) {

    suspend fun getInnboksEventsPerUser(): EventsPerUser {
        return database.dbQuery {
            measureInnboksEventsPerUser()
        }
    }

    suspend fun getVisibleInnboksEventsPerUser(): VisibleEventsPerUser {
        return database.dbQuery {
            measureVisibleInnboksEventsPerUser()
        }
    }

    suspend fun getActiveInnboksEventsPerUser(): ActiveEventsPerUser {
        return database.dbQuery {
            measureActiveInnboksEventsPerUser()
        }
    }

    suspend fun getInnboksEventActiveRate(): EventActiveRatePerUser {
        return database.dbQuery {
            measureInnboksEventActiveRatePerUser()
        }
    }

    suspend fun getInnboksEventsPerGroupId(): EventsPerGroupId {
        return database.dbQuery {
            measureInnboksEventsPerGroupId()
        }
    }

    suspend fun getInnboksGroupIdsPerUser(): GroupIdsPerUser {
        return database.dbQuery {
            measureInnboksGroupIdsPerUser()
        }
    }

    suspend fun getInnboksEventTextLength(): EventTextLength {
        return database.dbQuery {
            measureInnboksEventTextLength()
        }
    }

    suspend fun getNumberOfUsersWithInnboksEvents(): Int {
        return database.dbQuery {
            countUsersWithInnboksEvents()
        }
    }

    suspend fun getNumberOfInnboksEvents(): Int {
        return database.dbQuery {
            countNumberOfInnboksEvents()
        }
    }

    suspend fun getNumberOfVisibleInnboksEvents(): Int {
        return database.dbQuery {
            countNumberOfVisibleInnboksEvents()
        }
    }

    suspend fun getNumberOfActiveInnboksEvents(): Int {
        return database.dbQuery {
            countNumberOfActiveInnboksEvents()
        }
    }
}