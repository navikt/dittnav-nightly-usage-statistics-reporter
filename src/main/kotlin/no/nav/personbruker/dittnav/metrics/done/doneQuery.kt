package no.nav.personbruker.dittnav.metrics.done

import no.nav.personbruker.dittnav.metrics.database.mapSingleResult
import no.nav.personbruker.dittnav.metrics.database.query.cachedDoneEventsQueryString
import no.nav.personbruker.dittnav.metrics.database.query.toScalarInt
import java.sql.Connection

fun Connection.countNumberOfCachedDoneEvents(): Int =
    prepareStatement(cachedDoneEventsQueryString)
        .use {
            it.executeQuery().mapSingleResult {
                toScalarInt()
            }
        }