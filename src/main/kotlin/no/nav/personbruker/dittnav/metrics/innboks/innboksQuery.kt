package no.nav.personbruker.dittnav.metrics.innboks

import no.nav.personbruker.dittnav.metrics.database.entity.*
import no.nav.personbruker.dittnav.metrics.database.query.*
import java.sql.Connection

fun Connection.measureActiveInnboksEventsPerUser(): ActiveEventsPerUser =
    prepareStatement(innboksEventsActiveRateQueryString)
        .use {
            it.executeQuery().run {
                toActiveEventsPerUser()
            }
        }

fun Connection.measureInnboksEventActiveRate(): EventActiveRate =
    prepareStatement(innboksEventsActiveRateQueryString)
        .use {
            it.executeQuery().run {
                toEventActiveRate()
            }
        }

fun Connection.measureInnboksEventsPerGroupId(): EventsPerGroupId =
    prepareStatement(innboksEventsActiveRateQueryString)
        .use {
            it.executeQuery().run {
                toEventsPerGroupId()
            }
        }

fun Connection.measureInnboksEventsPerUser(): EventsPerUser =
    prepareStatement(innboksEventsPerUserQueryString)
        .use {
            it.executeQuery().run {
                toEventsPerUser()
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