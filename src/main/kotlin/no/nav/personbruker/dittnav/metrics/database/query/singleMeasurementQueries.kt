package no.nav.personbruker.dittnav.metrics.database.query

import no.nav.personbruker.dittnav.metrics.config.EventType
import java.sql.ResultSet

private fun usersQueryString(type: EventType) = "select count(distinct fodselsnummer) as scalar_value from ${type.eventType}"

val totalUsersQueryString = "select count(distinct fodselsnummer) as scalar_value from brukernotifikasjon_view"
val beskjedUsersQueryString = usersQueryString(EventType.BESKJED)
val oppgaveUsersQueryString = usersQueryString(EventType.OPPGAVE)
val innboksUsersQueryString = usersQueryString(EventType.INNBOKS)


private fun eventsQueryString(type: EventType) = "select count(1) as scalar_value from ${type.eventType}"

val totalEventsQueryString = "count(1) as scalar_value from brukernotifikasjon_view"
val beskjedEventsQueryString = eventsQueryString(EventType.BESKJED)
val oppgaveEventsQueryString = eventsQueryString(EventType.OPPGAVE)
val innboksEventsQueryString = eventsQueryString(EventType.INNBOKS)


val cachedDoneEventsQueryString = "select count(1) as scalar_value from done"

fun ResultSet.toScalarInt(): Int {
    return getInt("scalar_value")
}