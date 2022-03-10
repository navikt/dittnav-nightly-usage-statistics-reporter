package no.nav.personbruker.dittnav.metrics

import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import kotlin.system.exitProcess

val log = LoggerFactory.getLogger(Application::class.java)

fun main() = runBlocking {
    try {
        Application().runMetricsReporting()
    } catch (e: Exception) {
        log.warn("Failed to run metrics reporting", e)
        exitProcess(1)
    } finally {
        exitProcess(0)
    }
}
