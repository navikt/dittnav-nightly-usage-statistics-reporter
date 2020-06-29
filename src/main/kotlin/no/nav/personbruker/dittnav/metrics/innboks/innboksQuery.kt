package no.nav.personbruker.dittnav.metrics.innboks

import no.nav.personbruker.dittnav.metrics.database.entity.*
import no.nav.personbruker.dittnav.metrics.database.mapSingleResult
import no.nav.personbruker.dittnav.metrics.database.query.*
import java.sql.Connection

fun Connection.measureInnboksEventsPerUser(): EventsPerUser =
    prepareStatement(innboksEventsPerUserQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toEventsPerUser()
            }
        }

fun Connection.measureVisibleInnboksEventsPerUser(): VisibleEventsPerUser =
    prepareStatement(visibleInnboksEventsPerUserQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toVisibleEventsPerUser()
            }
        }

fun Connection.measureActiveInnboksEventsPerUser(): ActiveEventsPerUser =
    prepareStatement(activeInnboksEventsPerUserQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toActiveEventsPerUser()
            }
        }

fun Connection.measureInnboksEventActiveRatePerUser(): EventActiveRatePerUser =
    prepareStatement(innboksEventActiveRatePerUserQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toEventActiveRate()
            }
        }

fun Connection.measureInnboksEventsPerGroupId(): EventsPerGroupId =
    prepareStatement(innboksEventsPerGroupIdQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toEventsPerGroupId()
            }
        }

fun Connection.measureInnboksGroupIdsPerUser(): GroupIdsPerUser =
    prepareStatement(innboksGroupIdsPerUserQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toGroupIdsPerUser()
            }
        }

fun Connection.measureInnboksEventTextLength(): EventTextLength =
    prepareStatement(innboksEventTextLengthQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toEventTextLength()
            }
        }

fun Connection.countUsersWithInnboksEvents(): Int =
    prepareStatement(innboksUsersQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toScalarInt()
            }
        }

fun Connection.countNumberOfInnboksEvents(): Int =
    prepareStatement(innboksEventsQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toScalarInt()
            }
        }

fun Connection.countNumberOfVisibleInnboksEvents(): Int =
    prepareStatement(innboksEventsVisibleQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toScalarInt()
            }
        }

fun Connection.countNumberOfActiveInnboksEvents(): Int =
    prepareStatement(innboksEventsActiveQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toScalarInt()
            }
        }

