package no.nav.personbruker.dittnav.metrics.oppgave

import no.nav.personbruker.dittnav.metrics.oppgave.countNumberOfOppgaveEvents
import no.nav.personbruker.dittnav.metrics.oppgave.countUsersWithOppgaveEvents
import no.nav.personbruker.dittnav.metrics.database.Database
import no.nav.personbruker.dittnav.metrics.database.entity.*
import no.nav.personbruker.dittnav.metrics.oppgave.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class OppgaveRepository(private val database: Database) {

    private val log: Logger = getLogger(OppgaveRepository::class.java)

    suspend fun getActiveOppgaveEventsPerUser(): ActiveEventsPerUser {
        return database.dbQuery {
            measureActiveOppgaveEventsPerUser()
        }
    }

    suspend fun getOppgaveEventActiveRate(): EventActiveRate {
        return database.dbQuery {
            measureOppgaveEventActiveRate()
        }
    }

    suspend fun getOppgaveEventsPerGroupId(): EventsPerGroupId {
        return database.dbQuery {
            measureOppgaveEventsPerGroupId()
        }
    }

    suspend fun getOppgaveEventsPerUser(): EventsPerUser {
        return database.dbQuery {
            measureOppgaveEventsPerUser()
        }
    }

    suspend fun getOppgaveGroupIdsPerUser(): GroupIdsPerUser {
        return database.dbQuery {
            measureOppgaveGroupIdsPerUser()
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