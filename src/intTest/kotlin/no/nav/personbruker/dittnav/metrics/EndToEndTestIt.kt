package no.nav.personbruker.dittnav.metrics

import kotlinx.coroutines.runBlocking
import no.nav.personbruker.dittnav.metrics.config.TestApplicationContext
import org.junit.jupiter.api.Test

class EndToEndTestIt {

    val testContext = TestApplicationContext()

    val application = Application(testContext)

    @Test
    fun `should run to completion`() {
        runBlocking {
            application.runMetricsReporting()
        }
    }
}