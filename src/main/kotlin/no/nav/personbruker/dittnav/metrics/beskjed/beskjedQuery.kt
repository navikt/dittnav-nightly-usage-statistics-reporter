package no.nav.personbruker.dittnav.metrics.beskjed

import no.nav.personbruker.dittnav.metrics.database.entity.*
import no.nav.personbruker.dittnav.metrics.database.query.*
import java.sql.Connection

fun Connection.measureBeskjedEventsPerUser(): EventsPerUser =
    prepareStatement(beskjedEventsPerUserQueryString)
        .use {
            it.executeQuery().run {
                toEventsPerUser()
            }
        }

fun Connection.measureVisibleBeskjedEventsPerUser(): VisibleEventsPerUser =
    prepareStatement(visibleBeskjedEventsPerUserQueryString)
        .use {
            it.executeQuery().run {
                toVisibleEventsPerUser()
            }
        }

fun Connection.measureActiveBeskjedEventsPerUser(): ActiveEventsPerUser =
    prepareStatement(beskjedEventActiveRatePerUserQueryString)
        .use {
            it.executeQuery().run {
                toActiveEventsPerUser()
            }
        }

fun Connection.measureBeskjedEventActiveRatePerUser(): EventActiveRatePerUser =
    prepareStatement(beskjedEventActiveRatePerUserQueryString)
        .use {
            it.executeQuery().run {
                toEventActiveRate()
            }
        }

fun Connection.measureExpiredBeskjedEventsPerUser(): ExpiredEventsPerUser =
    prepareStatement(beskjedEventsExpiredPerUserQueryString)
        .use {
            it.executeQuery().run {
                toExpiredEventsPerUser()
            }
        }

fun Connection.measureBeskjedEventExpiredRatePerUser(): EventExpiredRatePerUser =
    prepareStatement(beskjedEventExpiredRatePerUserQueryString)
        .use {
            it.executeQuery().run {
                toEventExpiredRate()
            }
        }

fun Connection.measureBeskjedEventExpiredRatePerUserByInvisible(): EventExpiredRateByInvisiblePerUser =
    prepareStatement(beskjedEventExpiredRateByInvisiblePerUserQueryString)
        .use {
            it.executeQuery().run {
                toEventExpiredRateByInvisible()
            }
        }

fun Connection.measureBeskjedEventsPerGroupId(): EventsPerGroupId =
    prepareStatement(beskjedEventActiveRatePerUserQueryString)
        .use {
            it.executeQuery().run {
                toEventsPerGroupId()
            }
        }

fun Connection.measureBeskjedGroupIdsPerUser(): GroupIdsPerUser =
    prepareStatement(beskjedEventsPerUserQueryString)
        .use {
            it.executeQuery().run {
                toGroupIdsPerUser()
            }
        }

fun Connection.countUsersWithBeskjedEvents(): Int =
    prepareStatement(beskjedUsersQueryString)
        .use {
            it.executeQuery().run {
                toScalarInt()
            }
        }

fun Connection.countNumberOfBeskjedEvents(): Int =
    prepareStatement(beskjedEventsQueryString)
        .use {
            it.executeQuery().run {
                toScalarInt()
            }
        }

fun Connection.countNumberOfVisibleBeskjedEvents(): Int =
    prepareStatement(beskjedEventsVisibleQueryString)
        .use {
            it.executeQuery().run {
                toScalarInt()
            }
        }

fun Connection.countNumberOfActiveBeskjedEvents(): Int =
    prepareStatement(beskjedEventsActiveQueryString)
        .use {
            it.executeQuery().run {
                toScalarInt()
            }
        }

fun Connection.countNumberOfExpiredBeskjedEvents(): Int =
    prepareStatement(beskjedEventsExpiredQueryString)
        .use {
            it.executeQuery().run {
                toScalarInt()
            }
        }

