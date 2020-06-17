package no.nav.personbruker.dittnav.metrics.innboks

import no.nav.personbruker.dittnav.metrics.database.Database
import no.nav.personbruker.dittnav.metrics.database.entity.*
import no.nav.personbruker.dittnav.metrics.database.exception.NotYetImplementedException
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class InnboksRepository(private val database: Database) {

    private val log: Logger = getLogger(InnboksRepository::class.java)

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