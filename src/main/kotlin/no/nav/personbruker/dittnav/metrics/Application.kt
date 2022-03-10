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
        runDoneEventsMetricsReporting()
        runTotalEventsMetricsReporting()

        log.info("Finished metrics collection and reporting.")
    }
    
    private suspend fun runBeskjedMetricsReporting() {
        applicationContext.beskjedMetricsCollector.run {
            getAndReportBeskjedEventsPerUser()
            getAndReportActiveBeskjedEventsPerUser()
            getAndReportBeskjedEventActiveRate()
            getAndReportBeskjedEventsPerGroupId()
            getAndReportBeskjedGroupIdsPerUser()
            getAndReportBeskjedEventTextLength()

            getAndReportNumberOfUsersWithBeskjedEvents()
            getAndReportNumberOfBeskjedEvents()
            getAndReportNumberOfActiveBeskjedEvents()
        }
    }    
    
    private suspend fun runOppgaveMetricsReporting() {
        applicationContext.oppgaveMetricsCollector.run {
            getAndReportOppgaveEventsPerUser()
            getAndReportActiveOppgaveEventsPerUser()
            getAndReportOppgaveEventActiveRatePerUser()
            getAndReportOppgaveEventsPerGroupId()
            getAndReportOppgaveGroupIdsPerUser()
            getAndReportOppgaveEventTextLength()
            
            getAndReportNumberOfUsersWithOppgaveEvents()
            getAndReportNumberOfOppgaveEvents()
            getAndReportNumberOfActiveOppgaveEvents()
        }
    }

    private suspend fun runInnboksMetricsReporting() {
        applicationContext.innboksMetricsCollector.run {
            getAndReportInnboksEventsPerUser()
            getAndReportActiveInnboksEventsPerUser()
            getAndReportInnboksEventActiveRatePerUser()
            getAndReportInnboksEventsPerGroupId()
            getAndReportInnboksGroupIdsPerUser()
            getAndReportInnboksEventTextLength()

            getAndReportNumberOfUsersWithInnboksEvents()
            getAndReportNumberOfInnboksEvents()
            getAndReportNumberOfActiveInnboksEvents()
        }
    }

    private suspend fun runTotalEventsMetricsReporting() {
        applicationContext.totalEventsMetricsCollector.run {
            getAndReportEventsPerUser()
            getAndReportActiveEventsPerUser()
            getAndReportEventActiveRatePerUser()
            getAndReportEventsPerGroupId()
            getAndReportGroupIdsPerUser()
            getAndReportEventTextLength()

            getAndReportNumberOfUsersWithEvents()
            getAndReportNumberOfEvents()
            getAndReportNumberOfActiveEvents()
        }
    }

    private suspend fun runDoneEventsMetricsReporting() {
        applicationContext.doneMetricsCollector.run {
            getAndReportNumberOfCachedDoneEvents()
        }
    }
}
