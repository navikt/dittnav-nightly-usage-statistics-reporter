package no.nav.personbruker.dittnav.metrics.database.query

import no.nav.personbruker.dittnav.metrics.config.EventType
import no.nav.personbruker.dittnav.metrics.database.entity.EventExpiredRatePerUser
import java.sql.ResultSet

private fun singleTableQuery(type: EventType) = """
    select
        min(aggregate.rate) as minRate, 
        max(aggregate.rate) as maxRate, 
        avg(aggregate.rate)::decimal as avgRate,
        percentile_disc(0.25) within group ( order by aggregate.rate ) as "25th_percentile",
        percentile_disc(0.50) within group ( order by aggregate.rate ) as "50th_percentile",
        percentile_disc(0.75) within group ( order by aggregate.rate ) as "75th_percentile",
        percentile_disc(0.90) within group ( order by aggregate.rate ) as "90th_percentile",
        percentile_disc(0.99) within group ( order by aggregate.rate ) as "99th_percentile"
    from (select count(1) filter (where synligfremtil < now() and aktiv = true)::decimal / count(1)::decimal as rate from ${type.eventType} group by fodselsnummer) as aggregate
"""

val beskjedEventExpiredRatePerUserQueryString = singleTableQuery(EventType.BESKJED)

val totalEventExpiredRatePerUserQueryString = """
    select
        min(aggregate.rate) as minRate, 
        max(aggregate.rate) as maxRate, 
        avg(aggregate.rate)::decimal as avgRate,
        percentile_disc(0.25) within group ( order by aggregate.rate ) as "25th_percentile",
        percentile_disc(0.50) within group ( order by aggregate.rate ) as "50th_percentile",
        percentile_disc(0.75) within group ( order by aggregate.rate ) as "75th_percentile",
        percentile_disc(0.90) within group ( order by aggregate.rate ) as "90th_percentile",
        percentile_disc(0.99) within group ( order by aggregate.rate ) as "99th_percentile"
    from (
        select count(1) filter (where sft < now() and aktiv = true)::decimal / count(1)::decimal as rate from (
               SELECT synligfremtil as sft, aktiv, fodselsnummer FROM BESKJED
               UNION ALL
               SELECT null as sft, aktiv, fodselsnummer FROM OPPGAVE
               UNION ALL
               SELECT null as sft, aktiv, fodselsnummer FROM INNBOKS
           ) as inner_view group by fodselsnummer
        ) as aggregate
"""

fun ResultSet.toEventExpiredRate(): EventExpiredRatePerUser {
    return EventExpiredRatePerUser(
        min = getDouble("minRate"),
        max = getDouble("maxRate"),
        avg = getDouble("avgRate"),
        percentile25 = getDouble("25th_percentile"),
        percentile50 = getDouble("50th_percentile"),
        percentile75 = getDouble("75th_percentile"),
        percentile90 = getDouble("90th_percentile"),
        percentile99 = getDouble("99th_percentile")
    )
}