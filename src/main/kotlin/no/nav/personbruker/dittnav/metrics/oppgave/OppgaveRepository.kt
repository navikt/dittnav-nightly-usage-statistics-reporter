package no.nav.personbruker.dittnav.metrics.oppgave

import no.nav.personbruker.dittnav.metrics.database.Database
import no.nav.personbruker.dittnav.metrics.database.entity.*
import no.nav.personbruker.dittnav.metrics.database.exception.NotYetImplementedException
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class OppgaveRepository(private val database: Database) {

    private val log: Logger = getLogger(OppgaveRepository::class.java)

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
        throw NotYetImplementedException("Not implemented as field 'synligFremTil' does not exist for OPPGAVE")
    }

    suspend fun getExpiredOppgaveEventsPerUser(): ExpiredEventsPerUser {
        throw NotYetImplementedException("Not implemented as field 'synligFremTil' does not exist for OPPGAVE")
    }

    suspend fun measureOppgaveEventExpiredRate(): EventExpiredRatePerUser  {
        throw NotYetImplementedException("Not implemented as field 'synligFremTil' does not exist for OPPGAVE")

    }

    suspend fun getExpiredOppgaveEventPerUserByInvisible(): EventExpiredRateByInvisiblePerUser {
        throw NotYetImplementedException("Not implemented as field 'synligFremTil' does not exist for OPPGAVE")
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

    suspend fun getNumberOfExpiredOppgaveEvents(): Int {
        throw NotYetImplementedException("Not implemented as field 'synligFremTil' does not exist for OPPGAVE")
    }
}