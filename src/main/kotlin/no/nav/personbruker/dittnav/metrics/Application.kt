package no.nav.personbruker.dittnav.metrics

import no.nav.personbruker.dittnav.metrics.config.ApplicationContext
import no.nav.personbruker.dittnav.metrics.config.DefaultApplicationContext
import org.slf4j.LoggerFactory

class Application (val applicationContext: ApplicationContext = DefaultApplicationContext()) {

    val log = LoggerFactory.getLogger(Application::class.java)

    suspend fun runMetricsReporting() {
        log.info("Initiating collection and reporting of dittnav cache metrics.")
        runBeskjedMetricsReporting()
        runOppgaveMetricsReporting()
        runInnboksMetricsReporting()
        runTotalEventsMetricsReporting()
        log.info("Finished metrics collection and reporting.")
    }

    private suspend fun runBeskjedMetricsReporting() {
        applicationContext.beskjedMetricsCollector.run {
            getAndReportBeskjedEventsPerUser()
            getAndReportActiveBeskjedEventsPerUser()
            getAndReportBeskjedEventTextLength()
            getAndReportActiveBeskjedFrequencies()

            getAndReportNumberOfUsersWithBeskjedEvents()
            getAndReportNumberOfBeskjedEvents()
        }
    }

    private suspend fun runOppgaveMetricsReporting() {
        applicationContext.oppgaveMetricsCollector.run {
            getAndReportOppgaveEventsPerUser()
            getAndReportActiveOppgaveEventsPerUser()
            getAndReportOppgaveEventTextLength()
            getAndReportActiveOppgaveFrequencies()

            getAndReportNumberOfUsersWithOppgaveEvents()
            getAndReportNumberOfOppgaveEvents()
        }
    }

    private suspend fun runInnboksMetricsReporting() {
        applicationContext.innboksMetricsCollector.run {
            getAndReportInnboksEventsPerUser()
            getAndReportActiveInnboksEventsPerUser()
            getAndReportInnboksEventTextLength()
            getAndReportActiveInnboksFrequencies()

            getAndReportNumberOfUsersWithInnboksEvents()
            getAndReportNumberOfInnboksEvents()
        }
    }

    private suspend fun runTotalEventsMetricsReporting() {
        applicationContext.totalEventsMetricsCollector.run {
            getAndReportEventsPerUser()
            getAndReportActiveEventsPerUser()
            getAndReportEventTextLength()

            getAndReportNumberOfUsersWithEvents()
            getAndReportNumberOfEvents()
        }
    }
}
