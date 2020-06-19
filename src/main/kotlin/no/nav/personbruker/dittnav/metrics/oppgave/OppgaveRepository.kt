package no.nav.personbruker.dittnav.metrics.oppgave

import no.nav.personbruker.dittnav.metrics.database.Database
import no.nav.personbruker.dittnav.metrics.database.entity.*

class OppgaveRepository(private val database: Database) {

    suspend fun getOppgaveEventsPerUser(): EventsPerUser {
        return database.dbQuery {
            measureOppgaveEventsPerUser()
        }
    }

    suspend fun getVisibleOppgaveEventsPerUser(): VisibleEventsPerUser {
        return database.dbQuery {
            measureVisibleOppgaveEventsPerUser()
        }
    }

    suspend fun getActiveOppgaveEventsPerUser(): ActiveEventsPerUser {
        return database.dbQuery {
            measureActiveOppgaveEventsPerUser()
        }
    }

    suspend fun getOppgaveEventActiveRate(): EventActiveRatePerUser {
        return database.dbQuery {
            measureOppgaveEventActiveRate()
        }
    }

    suspend fun getOppgaveEventsPerGroupId(): EventsPerGroupId {
        return database.dbQuery {
            measureOppgaveEventsPerGroupId()
        }
    }

    suspend fun getOppgaveGroupIdsPerUser(): GroupIdsPerUser {
        return database.dbQuery {
            measureOppgaveGroupIdsPerUser()
        }
    }

    suspend fun getOppgaveEventTextLength(): EventTextLength {
        return database.dbQuery {
            measureOppgaveEventTextLength()
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

    suspend fun getNumberOfVisibleOppgaveEvents(): Int {
        return database.dbQuery {
            countNumberOfVisibleOppgaveEvents()
        }
    }

    suspend fun getNumberOfActiveOppgaveEvents(): Int {
        return database.dbQuery {
            countNumberOfActiveOppgaveEvents()
        }
    }
}