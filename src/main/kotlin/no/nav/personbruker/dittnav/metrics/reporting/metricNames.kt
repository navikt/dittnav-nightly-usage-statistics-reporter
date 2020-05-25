package no.nav.personbruker.dittnav.metrics.reporting

private const val METRIC_NAMESPACE = "dittnav.brukernotifikasjon.cache.v1"

const val EVENTS_PER_USER = "$METRIC_NAMESPACE.eventsPerUser"
const val VISIBLE_EVENTS_PER_USER = "$METRIC_NAMESPACE.visibleEventsPerUser"
const val ACTIVE_EVENTS_PER_USER = "$METRIC_NAMESPACE.activeEventsPerUser"
const val EVENT_ACTIVE_RATE_PER_USER = "$METRIC_NAMESPACE.eventActiveRatePerUser"
const val EXPIRED_EVENTS_PER_USER = "$METRIC_NAMESPACE.expiredEventsPerUser"
const val EVENT_EXPIRED_RATE_PER_USER = "$METRIC_NAMESPACE.eventExpiredRatePerUser"
const val EVENT_EXPIRED_RATE_PER_USER_BY_INVISIBLE = "$METRIC_NAMESPACE.eventExpiredRatePerUserByInvisible"
const val EVENTS_PER_GROUP_ID = "$METRIC_NAMESPACE.eventsPerGroupId"
const val GROUP_IDS_PER_USER = "$METRIC_NAMESPACE.groupIdsPerUser"
const val USERS_WITH_EVENTS = "$METRIC_NAMESPACE.usersWithEvents"
const val NUMBER_OF_EVENTS = "$METRIC_NAMESPACE.numberOfEvents"
const val NUMBER_OF_VISIBLE_EVENTS = "$METRIC_NAMESPACE.numberOfVisibleEvents"
const val NUMBER_OF_ACTIVE_EVENTS = "$METRIC_NAMESPACE.numberOfActiveEvents"
const val NUMBER_OF_EXPIRED_EVENTS = "$METRIC_NAMESPACE.numberOfExpiredEvents"
const val NUMBER_OF_CACHED_DONE_EVENTS = "$METRIC_NAMESPACE.numberOfCachedDoneEvents"