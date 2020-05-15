package no.nav.personbruker.dittnav.metrics.database.query

import no.nav.personbruker.dittnav.metrics.config.EventType
import no.nav.personbruker.dittnav.metrics.database.entity.EventActiveRate
import java.sql.ResultSet

private fun singleTableQueryString(type: EventType) = """
    select
        min(aggregate.rate) as minRate, 
        max(aggregate.rate) as maxRate,
        avg(aggregate.rate)::decimal as avgRate,
        percentile_disc(0.25) within group ( order by aggregate.rate ) as "25th_percentile",
        percentile_disc(0.50) within group ( order by aggregate.rate ) as "50th_percentile",
        percentile_disc(0.75) within group ( order by aggregate.rate ) as "75th_percentile",
        percentile_disc(0.90) within group ( order by aggregate.rate ) as "90th_percentile",
        percentile_disc(0.99) within group ( order by aggregate.rate ) as "99th_percentile"
    from (select count(1) filter ( where aktiv )::decimal / count(1)::decimal as rate from ${type.eventType} group by fodselsnummer) as aggregate;
"""

val beskjedEventsActiveRateQueryString = singleTableQueryString(EventType.BESKJED)
val oppgaveEventsActiveRateQueryString = singleTableQueryString(EventType.OPPGAVE)
val innboksEventsActiveRateQueryString = singleTableQueryString(EventType.INNBOKS)

val allEventsActiveRateQueryString = """
    select
        min(aggregate.rate) as minRate, 
        max(aggregate.rate) as maxRate,
        avg(aggregate.rate)::decimal as avgRate,
        percentile_disc(0.25) within group ( order by aggregate.rate ) as "25th_percentile",
        percentile_disc(0.50) within group ( order by aggregate.rate ) as "50th_percentile",
        percentile_disc(0.75) within group ( order by aggregate.rate ) as "75th_percentile",
        percentile_disc(0.90) within group ( order by aggregate.rate ) as "90th_percentile",
        percentile_disc(0.99) within group ( order by aggregate.rate ) as "99th_percentile"
    from (select count(1) filter ( where aktiv = false )::decimal / count(1)::decimal as rate from brukernotifikasjon_view group by fodselsnummer) as aggregate
"""

fun ResultSet.toEventActiveRate(): EventActiveRate {
    return EventActiveRate(
        min = getDouble("minEvents"),
        max = getDouble("maxEvents"),
        avg = getDouble("avgEvents"),
        percentile25 = getDouble("25th_percentile"),
        percentile50 = getDouble("50th_percentile"),
        percentile75 = getDouble("75th_percentile"),
        percentile90 = getDouble("90th_percentile"),
        percentile99 = getDouble("99th_percentile")
    )
}