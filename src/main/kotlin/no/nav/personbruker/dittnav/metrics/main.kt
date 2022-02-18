package no.nav.personbruker.dittnav.metrics

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import kotlin.system.exitProcess

val log = LoggerFactory.getLogger(Application::class.java)

fun main() = runBlocking {
    try {
        log.info("Venter for azure key mount")
        delay(30 * 1000)
        Application().runMetricsReporting()
    } finally {
        exitProcess(0)
    }
}
