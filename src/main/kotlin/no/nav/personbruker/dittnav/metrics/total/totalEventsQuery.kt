package no.nav.personbruker.dittnav.metrics.total

import no.nav.personbruker.dittnav.metrics.database.entity.*
import no.nav.personbruker.dittnav.metrics.database.mapSingleResult
import no.nav.personbruker.dittnav.metrics.database.query.*
import java.sql.Connection

fun Connection.measureEventsPerUser(): EventsPerUser =
    prepareStatement(totalEventsPerUserQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toEventsPerUser()
            }
        }

fun Connection.measureVisibleEventsPerUser(): VisibleEventsPerUser =
    prepareStatement(allVisibleEventsPerUserQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toVisibleEventsPerUser()
            }
        }

fun Connection.measureActiveEventsPerUser(): ActiveEventsPerUser =
    prepareStatement(totalActiveEventsPerUserQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toActiveEventsPerUser()
            }
        }

fun Connection.measureEventActiveRatePerUser(): EventActiveRatePerUser =
    prepareStatement(totalEventActiveRatePerUserQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toEventActiveRate()
            }
        }

fun Connection.measureExpiredEventsPerUser(): ExpiredEventsPerUser =
    prepareStatement(totalEventsExpiredPerUserQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toExpiredEventsPerUser()
            }
        }

fun Connection.measureEventExpiredRatePerUser(): EventExpiredRatePerUser =
    prepareStatement(totalEventExpiredRatePerUserQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toEventExpiredRate()
            }
        }

fun Connection.measureEventExpiredRatePerUserByInvisible(): EventExpiredRateByInvisiblePerUser =
    prepareStatement(totalEventExpiredRateByInvisiblePerUserQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toEventExpiredRateByInvisible()
            }
        }

fun Connection.measureEventsPerGroupId(): EventsPerGroupId =
    prepareStatement(totalEventsPerGroupIdQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toEventsPerGroupId()
            }
        }

fun Connection.measureGroupIdsPerUser(): GroupIdsPerUser =
    prepareStatement(totalGroupIdsPerUserQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toGroupIdsPerUser()
            }
        }


fun Connection.measureEventTextLength(): EventTextLength =
    prepareStatement(totalEventTextLengthQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toEventTextLength()
            }
        }

fun Connection.countUsersWithEvents(): Int =
    prepareStatement(totalUsersQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toScalarInt()
            }
        }

fun Connection.countNumberOfEvents(): Int =
    prepareStatement(totalEventsQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toScalarInt()
            }
        }

fun Connection.countNumberOfVisibleEvents(): Int =
    prepareStatement(totalEventsVisibleQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toScalarInt()
            }
        }

fun Connection.countNumberOfActiveEvents(): Int =
    prepareStatement(totalEventsActiveQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toScalarInt()
            }
        }

fun Connection.countNumberOfExpiredEvents(): Int =
    prepareStatement(totalEventsExpiredQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toScalarInt()
            }
        }