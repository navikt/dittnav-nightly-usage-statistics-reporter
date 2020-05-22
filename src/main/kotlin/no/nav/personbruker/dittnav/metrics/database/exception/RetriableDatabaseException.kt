package no.nav.personbruker.dittnav.metrics.database.exception

class RetriableDatabaseException(message: String, cause: Throwable?) : Exception(message, cause) {
    constructor(message: String) : this(message, null)
}