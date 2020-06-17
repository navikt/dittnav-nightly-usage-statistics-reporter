package no.nav.personbruker.dittnav.metrics.oppgave

import no.nav.personbruker.dittnav.metrics.database.entity.*
import no.nav.personbruker.dittnav.metrics.database.mapSingleResult
import no.nav.personbruker.dittnav.metrics.database.query.*
import java.sql.Connection


fun Connection.measureOppgaveEventsPerUser(): EventsPerUser =
    prepareStatement(oppgaveEventsPerUserQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toEventsPerUser()
            }
        }

fun Connection.measureVisibleOppgaveEventsPerUser(): VisibleEventsPerUser =
    prepareStatement(visibleOppgaveEventsPerUserQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toVisibleEventsPerUser()
            }
        }

fun Connection.measureActiveOppgaveEventsPerUser(): ActiveEventsPerUser =
    prepareStatement(activeOppgaveEventsPerUserQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toActiveEventsPerUser()
            }
        }

fun Connection.measureOppgaveEventActiveRate(): EventActiveRatePerUser =
    prepareStatement(oppgaveEventActiveRatePerUserQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toEventActiveRate()
            }
        }

fun Connection.measureOppgaveEventsPerGroupId(): EventsPerGroupId =
    prepareStatement(oppgaveEventsPerGroupIdQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toEventsPerGroupId()
            }
        }

fun Connection.measureOppgaveGroupIdsPerUser(): GroupIdsPerUser =
    prepareStatement(oppgaveGroupIdsPerUserQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toGroupIdsPerUser()
            }
        }

fun Connection.measureOppgaveEventTextLength(): EventTextLength =
    prepareStatement(oppgaveEventTextLengthQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toEventTextLength()
            }
        }

fun Connection.countUsersWithOppgaveEvents(): Int =
    prepareStatement(oppgaveUsersQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toScalarInt()
            }
        }

fun Connection.countNumberOfOppgaveEvents(): Int =
    prepareStatement(oppgaveEventsQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toScalarInt()
            }
        }

fun Connection.countNumberOfVisibleOppgaveEvents(): Int =
    prepareStatement(oppgaveEventsVisibleQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toScalarInt()
            }
        }

fun Connection.countNumberOfActiveOppgaveEvents(): Int =
    prepareStatement(oppgaveEventsActiveQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toScalarInt()
            }
        }

