package no.nav.personbruker.dittnav.metrics

import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    Application().runMetricsReporting()
}