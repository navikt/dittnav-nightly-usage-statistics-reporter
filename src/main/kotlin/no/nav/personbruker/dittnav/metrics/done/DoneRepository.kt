package no.nav.personbruker.dittnav.metrics.done

import no.nav.personbruker.dittnav.metrics.database.Database
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class DoneRepository(private val database: Database) {

    private val log: Logger = getLogger(DoneRepository::class.java)

    suspend fun getNumberOfCachedDoneEvents(): Int {
        return database.dbQuery {
            countNumberOfCachedDoneEvents()
        }
    }
}