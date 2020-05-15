package no.nav.personbruker.dittnav.metrics.oppgave

import no.nav.personbruker.dittnav.metrics.database.entity.*
import no.nav.personbruker.dittnav.metrics.database.query.*
import java.sql.Connection

fun Connection.measureActiveOppgaveEventsPerUser(): ActiveEventsPerUser =
    prepareStatement(oppgaveEventsActiveRateQueryString)
        .use {
            it.executeQuery().run {
                toActiveEventsPerUser()
            }
        }

fun Connection.measureOppgaveEventActiveRate(): EventActiveRate =
    prepareStatement(oppgaveEventsActiveRateQueryString)
        .use {
            it.executeQuery().run {
                toEventActiveRate()
            }
        }

fun Connection.measureOppgaveEventsPerGroupId(): EventsPerGroupId =
    prepareStatement(oppgaveEventsActiveRateQueryString)
        .use {
            it.executeQuery().run {
                toEventsPerGroupId()
            }
        }

fun Connection.measureOppgaveEventsPerUser(): EventsPerUser =
    prepareStatement(oppgaveEventsPerUserQueryString)
        .use {
            it.executeQuery().run {
                toEventsPerUser()
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
