package no.nav.personbruker.dittnav.metrics.oppgave

import no.nav.personbruker.dittnav.metrics.database.entity.*
import no.nav.personbruker.dittnav.metrics.database.query.*
import java.sql.Connection


fun Connection.measureOppgaveEventsPerUser(): EventsPerUser =
    prepareStatement(oppgaveEventsPerUserQueryString)
        .use {
            it.executeQuery().run {
                toEventsPerUser()
            }
        }

fun Connection.measureVisibleOppgaveEventsPerUser(): VisibleEventsPerUser =
    prepareStatement(visibleOppgaveEventsPerUserQueryString)
        .use {
            it.executeQuery().run {
                toVisibleEventsPerUser()
            }
        }

fun Connection.measureActiveOppgaveEventsPerUser(): ActiveEventsPerUser =
    prepareStatement(oppgaveEventActiveRatePerUserQueryString)
        .use {
            it.executeQuery().run {
                toActiveEventsPerUser()
            }
        }

fun Connection.measureOppgaveEventActiveRate(): EventActiveRatePerUser =
    prepareStatement(oppgaveEventActiveRatePerUserQueryString)
        .use {
            it.executeQuery().run {
                toEventActiveRate()
            }
        }

fun Connection.measureOppgaveEventsPerGroupId(): EventsPerGroupId =
    prepareStatement(oppgaveEventActiveRatePerUserQueryString)
        .use {
            it.executeQuery().run {
                toEventsPerGroupId()
            }
        }

fun Connection.measureOppgaveGroupIdsPerUser(): GroupIdsPerUser =
    prepareStatement(oppgaveEventsPerUserQueryString)
        .use {
            it.executeQuery().run {
                toGroupIdsPerUser()
            }
        }

fun Connection.countUsersWithOppgaveEvents(): Int =
    prepareStatement(oppgaveUsersQueryString)
        .use {
            it.executeQuery().run {
                toScalarInt()
            }
        }

fun Connection.countNumberOfOppgaveEvents(): Int =
    prepareStatement(oppgaveEventsQueryString)
        .use {
            it.executeQuery().run {
                toScalarInt()
            }
        }

fun Connection.countNumberOfVisibleOppgaveEvents(): Int =
    prepareStatement(oppgaveEventsVisibleQueryString)
        .use {
            it.executeQuery().run {
                toScalarInt()
            }
        }

fun Connection.countNumberOfActiveOppgaveEvents(): Int =
    prepareStatement(oppgaveEventsActiveQueryString)
        .use {
            it.executeQuery().run {
                toScalarInt()
            }
        }

