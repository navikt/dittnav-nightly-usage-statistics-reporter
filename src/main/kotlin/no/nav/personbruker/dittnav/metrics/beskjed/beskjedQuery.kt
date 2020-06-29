package no.nav.personbruker.dittnav.metrics.beskjed

import no.nav.personbruker.dittnav.metrics.database.entity.*
import no.nav.personbruker.dittnav.metrics.database.mapSingleResult
import no.nav.personbruker.dittnav.metrics.database.query.*
import java.sql.Connection

fun Connection.measureBeskjedEventsPerUser(): EventsPerUser =
    prepareStatement(beskjedEventsPerUserQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toEventsPerUser()
            }
        }

fun Connection.measureVisibleBeskjedEventsPerUser(): VisibleEventsPerUser =
    prepareStatement(visibleBeskjedEventsPerUserQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toVisibleEventsPerUser()
            }
        }

fun Connection.measureActiveBeskjedEventsPerUser(): ActiveEventsPerUser =
    prepareStatement(activeBeskjedEventsPerUserQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toActiveEventsPerUser()
            }
        }

fun Connection.measureBeskjedEventActiveRatePerUser(): EventActiveRatePerUser =
    prepareStatement(beskjedEventActiveRatePerUserQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toEventActiveRate()
            }
        }

fun Connection.measureExpiredBeskjedEventsPerUser(): ExpiredEventsPerUser =
    prepareStatement(beskjedEventsExpiredPerUserQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toExpiredEventsPerUser()
            }
        }

fun Connection.measureBeskjedEventExpiredRatePerUser(): EventExpiredRatePerUser =
    prepareStatement(beskjedEventExpiredRatePerUserQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toEventExpiredRate()
            }
        }

fun Connection.measureBeskjedEventExpiredRatePerUserByInvisible(): EventExpiredRateByInvisiblePerUser =
    prepareStatement(beskjedEventExpiredRateByInvisiblePerUserQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toEventExpiredRateByInvisible()
            }
        }

fun Connection.measureBeskjedEventsPerGroupId(): EventsPerGroupId =
    prepareStatement(beskjedEventsPerGroupIdQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toEventsPerGroupId()
            }
        }

fun Connection.measureBeskjedGroupIdsPerUser(): GroupIdsPerUser =
    prepareStatement(beskjedGroupIdsPerUserQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toGroupIdsPerUser()
            }
        }

fun Connection.measureBeskjedEventTextLength(): EventTextLength =
    prepareStatement(beskjedEventTextLengthQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toEventTextLength()
            }
        }

fun Connection.countUsersWithBeskjedEvents(): Int =
    prepareStatement(beskjedUsersQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toScalarInt()
            }
        }

fun Connection.countNumberOfBeskjedEvents(): Int =
    prepareStatement(beskjedEventsQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toScalarInt()
            }
        }

fun Connection.countNumberOfVisibleBeskjedEvents(): Int =
    prepareStatement(beskjedEventsVisibleQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toScalarInt()
            }
        }

fun Connection.countNumberOfActiveBeskjedEvents(): Int =
    prepareStatement(beskjedEventsActiveQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toScalarInt()
            }
        }

fun Connection.countNumberOfExpiredBeskjedEvents(): Int =
    prepareStatement(beskjedEventsExpiredQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toScalarInt()
            }
        }

