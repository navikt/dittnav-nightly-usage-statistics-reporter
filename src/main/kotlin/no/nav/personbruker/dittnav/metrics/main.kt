package no.nav.personbruker.dittnav.metrics

import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val application = Application()
    application.runMetricsReporting()
}