package no.nav.personbruker.dittnav.metrics.database.entity

data class IntegerMeasurement (
    val min: Int,
    val max: Int,
    val avg: Int,
    val percentile25: Int,
    val percentile50: Int,
    val percentile75: Int,
    val percentile90: Int,
    val percentile99: Int
)

typealias EventsPerUser = IntegerMeasurement
typealias ActiveEventsPerUser = IntegerMeasurement
typealias EventsPerGroupId = IntegerMeasurement
typealias GroupIdsPerUser = IntegerMeasurement