package no.nav.personbruker.dittnav.metrics.database.query

import no.nav.personbruker.dittnav.metrics.config.EventType
import no.nav.personbruker.dittnav.metrics.database.entity.EventsPerGroupId
import java.sql.ResultSet

private fun singleTableQueryString(type: EventType) = """
    select
        min(aggregate.events) as minEvents,
        max(aggregate.events) as maxEvents,
        avg(aggregate.events)::int as avgEvents,
        percentile_disc(0.25) within group ( order by aggregate.events ) as "25th_percentile",
        percentile_disc(0.50) within group ( order by aggregate.events ) as "50th_percentile",
        percentile_disc(0.75) within group ( order by aggregate.events ) as "75th_percentile",
        percentile_disc(0.90) within group ( order by aggregate.events ) as "90th_percentile",
        percentile_disc(0.99) within group ( order by aggregate.events ) as "99th_percentile"
    from (select count(1) as events from ${type.eventType} group by produsent, fodselsnummer, grupperingsid) as aggregate 
"""

val beskjedEventsPerGroupIdQueryString = singleTableQueryString(EventType.BESKJED)
val oppgaveEventsPerGroupIdQueryString = singleTableQueryString(EventType.OPPGAVE)
val innboksEventsPerGroupIdQueryString = singleTableQueryString(EventType.INNBOKS)

val allEventsPerGroupIdQueryString = """
    select
        min(aggregate.events) as minEvents,
        max(aggregate.events) as maxEvents,
        avg(aggregate.events)::int as avgEvents,
        percentile_disc(0.25) within group ( order by aggregate.events ) as "25th_percentile",
        percentile_disc(0.50) within group ( order by aggregate.events ) as "50th_percentile",
        percentile_disc(0.75) within group ( order by aggregate.events ) as "75th_percentile",
        percentile_disc(0.90) within group ( order by aggregate.events ) as "90th_percentile",
        percentile_disc(0.99) within group ( order by aggregate.events ) as "99th_percentile"
    from (
        select count(1) as events from (
            SELECT produsent, fodselsnummer, grupperingsid FROM BESKJED
                UNION ALL
            SELECT produsent, fodselsnummer, grupperingsid FROM OPPGAVE
                UNION ALL
            SELECT produsent, fodselsnummer, grupperingsid FROM INNBOKS
            ) as inner_view group by produsent, fodselsnummer, grupperingsid
        ) as aggregate
"""

fun ResultSet.toEventsPerGroupId(): EventsPerGroupId {
    return EventsPerGroupId(
        min = getInt("minEvents"),
        max = getInt("maxEvents"),
        avg = getInt("avgEvents"),
        percentile25 = getInt("25th_percentile"),
        percentile50 = getInt("50th_percentile"),
        percentile75 = getInt("75th_percentile"),
        percentile90 = getInt("90th_percentile"),
        percentile99 = getInt("99th_percentile")
    )
}