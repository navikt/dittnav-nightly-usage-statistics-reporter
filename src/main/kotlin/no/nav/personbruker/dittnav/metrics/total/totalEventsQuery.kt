package no.nav.personbruker.dittnav.metrics.all

import no.nav.personbruker.dittnav.metrics.database.entity.*
import no.nav.personbruker.dittnav.metrics.database.query.*
import java.sql.Connection

fun Connection.measureEventsPerUser(): EventsPerUser =
    prepareStatement(allEventsPerUserQueryString)
        .use {
            it.executeQuery().run {
                toEventsPerUser()
            }
        }

fun Connection.measureVisibleEventsPerUser(): VisibleEventsPerUser =
    prepareStatement(allVisibleEventsPerUserQueryString)
        .use {
            it.executeQuery().run {
                toVisibleEventsPerUser()
            }
        }

fun Connection.measureActiveEventsPerUser(): ActiveEventsPerUser =
    prepareStatement(totalEventActiveRatePerUserQueryString)
        .use {
            it.executeQuery().run {
                toActiveEventsPerUser()
            }
        }

fun Connection.measureEventActiveRatePerUser(): EventActiveRatePerUser =
    prepareStatement(totalEventActiveRatePerUserQueryString)
        .use {
            it.executeQuery().run {
                toEventActiveRate()
            }
        }

fun Connection.measureExpiredEventsPerUser(): ExpiredEventsPerUser =
    prepareStatement(allEventsExpiredPerUserQueryString)
        .use {
            it.executeQuery().run {
                toExpiredEventsPerUser()
            }
        }

fun Connection.measureEventExpiredRatePerUser(): EventExpiredRatePerUser =
    prepareStatement(allEventExpiredRatePerUserQueryString)
        .use {
            it.executeQuery().run {
                toEventExpiredRate()
            }
        }

fun Connection.measureEventExpiredRatePerUserByInvisible(): EventExpiredRateByInvisiblePerUser =
    prepareStatement(totalEventExpiredRateByInvisiblePerUserQueryString)
        .use {
            it.executeQuery().run {
                toEventExpiredRateByInvisible()
            }
        }

fun Connection.measureEventsPerGroupId(): EventsPerGroupId =
    prepareStatement(totalEventActiveRatePerUserQueryString)
        .use {
            it.executeQuery().run {
                toEventsPerGroupId()
            }
        }

fun Connection.measureGroupIdsPerUser(): GroupIdsPerUser =
    prepareStatement(allEventsPerUserQueryString)
        .use {
            it.executeQuery().run {
                toGroupIdsPerUser()
            }
        }

fun Connection.countUsersWithEvents(): Int =
    prepareStatement(totalUsersQueryString)
        .use {
            it.executeQuery().run {
                toScalarInt()
            }
        }

fun Connection.countNumberOfEvents(): Int =
    prepareStatement(totalEventsQueryString)
        .use {
            it.executeQuery().run {
                toScalarInt()
            }
        }

fun Connection.countNumberOfVisibleEvents(): Int =
    prepareStatement(totalEventsVisibleQueryString)
        .use {
            it.executeQuery().run {
                toScalarInt()
            }
        }

fun Connection.countNumberOfActiveEvents(): Int =
    prepareStatement(totalEventsActiveQueryString)
        .use {
            it.executeQuery().run {
                toScalarInt()
            }
        }

fun Connection.countNumberOfExpiredEvents(): Int =
    prepareStatement(totalEventsExpiredQueryString)
        .use {
            it.executeQuery().run {
                toScalarInt()
            }
        }