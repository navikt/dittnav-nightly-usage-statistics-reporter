package no.nav.personbruker.dittnav.metrics.reporting

import no.nav.personbruker.dittnav.metrics.database.entity.IntegerMeasurement

data class EventCountForProducer(
    val namespace: String,
    val appName: String,
    val count: Int
)

fun EventCountForProducer.toIntegerMeasurement() = IntegerMeasurement(
    count,
    count,
    count.toDouble(),
    count,
    count,
    count,
    count,
    count
)
