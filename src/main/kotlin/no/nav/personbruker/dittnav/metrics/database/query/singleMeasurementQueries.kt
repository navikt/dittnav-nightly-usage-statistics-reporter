package no.nav.personbruker.dittnav.metrics.database.query

import no.nav.personbruker.dittnav.metrics.config.EventType
import java.sql.ResultSet

private fun usersQueryString(type: EventType) = "select count(distinct fodselsnummer) as scalar_value from ${type.eventType}"

val totalUsersQueryString = "select count(distinct fodselsnummer) as scalar_value from brukernotifikasjon_view"
val beskjedUsersQueryString = usersQueryString(EventType.BESKJED)
val oppgaveUsersQueryString = usersQueryString(EventType.OPPGAVE)
val innboksUsersQueryString = usersQueryString(EventType.INNBOKS)



private fun eventsQueryString(type: EventType) = "select count(1) as scalar_value from ${type.eventType}"

val totalEventsQueryString = "select count(1) as scalar_value from brukernotifikasjon_view"
val beskjedEventsQueryString = eventsQueryString(EventType.BESKJED)
val oppgaveEventsQueryString = eventsQueryString(EventType.OPPGAVE)
val innboksEventsQueryString = eventsQueryString(EventType.INNBOKS)



private fun eventsVisibleQueryString(type: EventType) = "select count(1) as scalar_value from ${type.eventType} where (synligfremtil > now() or synligfremtil is null) and aktiv"

val totalEventsVisibleQueryString = """
    select count(1) as scalar_value
    from (
             SELECT synligfremtil as sft, aktiv, fodselsnummer FROM BESKJED
             UNION ALL
             SELECT null as sft, aktiv, fodselsnummer FROM OPPGAVE
             UNION ALL
             SELECT null as sft, aktiv, fodselsnummer FROM INNBOKS
         ) as aggregate where (sft > now() or sft is null) and aktiv = true
"""
val beskjedEventsVisibleQueryString = eventsVisibleQueryString(EventType.BESKJED)
val oppgaveEventsVisibleQueryString = eventsActiveQueryString(EventType.OPPGAVE) // Currently, OPPGAVE and BESKJED does not have field 'synligfremtil',
val innboksEventsVisibleQueryString = eventsActiveQueryString(EventType.INNBOKS) // and visibility is determined solely by field 'aktiv'




private fun eventsActiveQueryString(type: EventType) = "select count(1) as scalar_value from ${type.eventType} where aktiv"

val totalEventsActiveQueryString = "select count(1) as scalar_value from brukernotifikasjon_view where aktiv"
val beskjedEventsActiveQueryString = eventsActiveQueryString(EventType.BESKJED)
val oppgaveEventsActiveQueryString = eventsActiveQueryString(EventType.OPPGAVE)
val innboksEventsActiveQueryString = eventsActiveQueryString(EventType.INNBOKS)



private fun eventsExpiredQueryString(type: EventType) = "select count(1) as scalar_value from ${type.eventType} where synligfremtil < now() and aktiv"

val totalEventsExpiredQueryString = """
    select count(1) as scalar_value
    from (
        SELECT synligfremtil as sft, aktiv, fodselsnummer FROM BESKJED
        UNION ALL
        SELECT null as sft, aktiv, fodselsnummer FROM OPPGAVE
        UNION ALL
        SELECT null as sft, aktiv, fodselsnummer FROM INNBOKS
    ) as aggregate where (sft < now()) and aktiv = true
"""
val beskjedEventsExpiredQueryString = eventsExpiredQueryString(EventType.BESKJED)



val cachedDoneEventsQueryString = "select count(1) as scalar_value from done"

fun ResultSet.toScalarInt(): Int {
    return getInt("scalar_value")
}