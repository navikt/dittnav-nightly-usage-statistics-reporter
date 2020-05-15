package no.nav.personbruker.dittnav.metrics.beskjed

import no.nav.personbruker.dittnav.metrics.database.entity.*
import no.nav.personbruker.dittnav.metrics.database.query.*
import java.sql.Connection

fun Connection.measureActiveBeskjedEventsPerUser(): ActiveEventsPerUser =
    prepareStatement(beskjedEventsActiveRateQueryString)
        .use {
            it.executeQuery().run {
                toActiveEventsPerUser()
            }
        }

fun Connection.measureBeskjedEventActiveRate(): EventActiveRate =
    prepareStatement(beskjedEventsActiveRateQueryString)
        .use {
            it.executeQuery().run {
                toEventActiveRate()
            }
        }

fun Connection.measureBeskjedEventsPerGroupId(): EventsPerGroupId =
    prepareStatement(beskjedEventsActiveRateQueryString)
        .use {
            it.executeQuery().run {
                toEventsPerGroupId()
            }
        }

fun Connection.measureBeskjedEventsPerUser(): EventsPerUser =
    prepareStatement(beskjedEventsPerUserQueryString)
        .use {
            it.executeQuery().run {
                toEventsPerUser()
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

