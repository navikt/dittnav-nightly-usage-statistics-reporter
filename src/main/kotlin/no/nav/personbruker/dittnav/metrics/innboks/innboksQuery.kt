package no.nav.personbruker.dittnav.metrics.innboks

import no.nav.personbruker.dittnav.metrics.database.entity.*
import no.nav.personbruker.dittnav.metrics.database.query.*
import java.sql.Connection

fun Connection.measureInnboksEventsPerUser(): EventsPerUser =
    prepareStatement(innboksEventsPerUserQueryString)
        .use {
            it.executeQuery().run {
                toEventsPerUser()
            }
        }

fun Connection.measureVisibleInnboksEventsPerUser(): VisibleEventsPerUser =
    prepareStatement(visibleInnboksEventsPerUserQueryString)
        .use {
            it.executeQuery().run {
                toVisibleEventsPerUser()
            }
        }

fun Connection.measureActiveInnboksEventsPerUser(): ActiveEventsPerUser =
    prepareStatement(activeInnboksEventsPerUserQueryString)
        .use {
            it.executeQuery().run {
                toActiveEventsPerUser()
            }
        }

fun Connection.measureInnboksEventActiveRate(): EventActiveRatePerUser =
    prepareStatement(innboksEventActiveRatePerUserQueryString)
        .use {
            it.executeQuery().run {
                toEventActiveRate()
            }
        }

fun Connection.measureInnboksEventsPerGroupId(): EventsPerGroupId =
    prepareStatement(innboksEventActiveRatePerUserQueryString)
        .use {
            it.executeQuery().run {
                toEventsPerGroupId()
            }
        }

fun Connection.measureInnboksGroupIdsPerUser(): GroupIdsPerUser =
    prepareStatement(innboksEventsPerUserQueryString)
        .use {
            it.executeQuery().run {
                toGroupIdsPerUser()
            }
        }

fun Connection.countUsersWithInnboksEvents(): Int =
    prepareStatement(innboksUsersQueryString)
        .use {
            it.executeQuery().run {
                toScalarInt()
            }
        }

fun Connection.countNumberOfInnboksEvents(): Int =
    prepareStatement(innboksEventsQueryString)
        .use {
            it.executeQuery().run {
                toScalarInt()
            }
        }

fun Connection.countNumberOfVisibleInnboksEvents(): Int =
    prepareStatement(innboksEventsVisibleQueryString)
        .use {
            it.executeQuery().run {
                toScalarInt()
            }
        }

fun Connection.countNumberOfActiveInnboksEvents(): Int =
    prepareStatement(innboksEventsActiveQueryString)
        .use {
            it.executeQuery().run {
                toScalarInt()
            }
        }

