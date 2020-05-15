package no.nav.personbruker.dittnav.metrics.config

import no.nav.personbruker.dittnav.metrics.beskjed.BeskjedRepository
import no.nav.personbruker.dittnav.metrics.database.Database
import no.nav.personbruker.dittnav.metrics.database.PostgresDatabase
import no.nav.personbruker.dittnav.metrics.done.DoneRepository
import no.nav.personbruker.dittnav.metrics.innboks.InnboksRepository
import no.nav.personbruker.dittnav.metrics.oppgave.OppgaveRepository
import no.nav.personbruker.dittnav.total.BrukernotifikasjonViewRepository

class ApplicationContext {

    var environment = Environment()
    val database: Database = PostgresDatabase(environment)

    val beskjedRepository = BeskjedRepository(database)
    val oppgaveRepository = OppgaveRepository(database)
    val innboksRepository = InnboksRepository(database)
    val doneRepository = DoneRepository(database)
    val brukernotifikasjonViewRepository = BrukernotifikasjonViewRepository(database)

}