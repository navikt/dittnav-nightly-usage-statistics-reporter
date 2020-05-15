package no.nav.personbruker.dittnav.metrics.innboks

import no.nav.personbruker.dittnav.metrics.oppgave.countNumberOfOppgaveEvents
import no.nav.personbruker.dittnav.metrics.oppgave.countUsersWithOppgaveEvents
import no.nav.personbruker.dittnav.metrics.database.Database
import no.nav.personbruker.dittnav.metrics.database.entity.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class InnboksRepository(private val database: Database) {

    private val log: Logger = getLogger(InnboksRepository::class.java)

    suspend fun getActiveInnboksEventsPerUser(): ActiveEventsPerUser {
        return database.dbQuery {
            measureActiveInnboksEventsPerUser()
        }
    }

    suspend fun getInnboksEventActiveRate(): EventActiveRate {
        return database.dbQuery {
            measureInnboksEventActiveRate()
        }
    }

    suspend fun getInnboksEventsPerGroupId(): EventsPerGroupId {
        return database.dbQuery {
            measureInnboksEventsPerGroupId()
        }
    }

    suspend fun getInnboksEventsPerUser(): EventsPerUser {
        return database.dbQuery {
            measureInnboksEventsPerUser()
        }
    }

    suspend fun getInnboksGroupIdsPerUser(): GroupIdsPerUser {
        return database.dbQuery {
            measureInnboksGroupIdsPerUser()
        }
    }

    suspend fun getNumberOfUsersWithOppgaveEvents(): Int {
        return database.dbQuery {
            countUsersWithOppgaveEvents()
        }
    }

    suspend fun getNumberOfOppgaveEvents(): Int {
        return database.dbQuery {
            countNumberOfOppgaveEvents()
        }
    }
}