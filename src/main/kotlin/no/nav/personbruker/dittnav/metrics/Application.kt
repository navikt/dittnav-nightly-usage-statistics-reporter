package no.nav.personbruker.dittnav.metrics

import no.nav.personbruker.dittnav.metrics.config.ApplicationContext
import no.nav.personbruker.dittnav.metrics.config.DefaultApplicationContext
import org.slf4j.LoggerFactory

class Application (val applicationContext: ApplicationContext = DefaultApplicationContext()) {

    val log = LoggerFactory.getLogger(Application::class.java)

    suspend fun runMetricsReporting() {
        log.info("Initiating collection and reporting of dittnav cache metrics.")
        runBeskjedMetricsReporting()
//        runOppgaveMetricsReporting()
//        runInnboksMetricsReporting()
//        runTotalEventsMetricsReporting()
//        runDoneEventsMetricsReporting()

        log.info("Finished metrics collection and reporting.")
    }
    
    private suspend fun runBeskjedMetricsReporting() {
        applicationContext.beskjedMetricsCollector.run {
            getAndReportBeskjedEventsPerUser()
      /*      getAndReportVisibleBeskjedEventsPerUser()
            getAndReportActiveBeskjedEventsPerUser()
            getAndReportBeskjedEventActiveRate()
            getAndReportExpiredBeskjedEventsPerUser()
            getAndReportBeskjedEventExpiredRate()
            getAndReportExpiredBeskjedEventPerUserByInvisible()
            getAndReportBeskjedEventsPerGroupId()
            getAndReportBeskjedGroupIdsPerUser()
            getAndReportBeskjedEventTextLength()

            getAndReportNumberOfUsersWithBeskjedEvents()
            getAndReportNumberOfBeskjedEvents()
            getAndReportNumberOfVisibleBeskjedEvents()
            getAndReportNumberOfActiveBeskjedEvents()
            getAndReportNumberOfExpiredBeskjedEvents()*/
        }
    }    
    
    private suspend fun runOppgaveMetricsReporting() {
        applicationContext.oppgaveMetricsCollector.run {
            getAndReportOppgaveEventsPerUser()
            getAndReportVisibleOppgaveEventsPerUser()
            getAndReportActiveOppgaveEventsPerUser()
            getAndReportOppgaveEventActiveRatePerUser()
            getAndReportOppgaveEventsPerGroupId()
            getAndReportOppgaveGroupIdsPerUser()
            getAndReportOppgaveEventTextLength()
            
            getAndReportNumberOfUsersWithOppgaveEvents()
            getAndReportNumberOfOppgaveEvents()
            getAndReportNumberOfVisibleOppgaveEvents()
            getAndReportNumberOfActiveOppgaveEvents()
        }
    }

    private suspend fun runInnboksMetricsReporting() {
        applicationContext.innboksMetricsCollector.run {
            getAndReportInnboksEventsPerUser()
            getAndReportVisibleInnboksEventsPerUser()
            getAndReportActiveInnboksEventsPerUser()
            getAndReportInnboksEventActiveRatePerUser()
            getAndReportInnboksEventsPerGroupId()
            getAndReportInnboksGroupIdsPerUser()
            getAndReportInnboksEventTextLength()

            getAndReportNumberOfUsersWithInnboksEvents()
            getAndReportNumberOfInnboksEvents()
            getAndReportNumberOfVisibleInnboksEvents()
            getAndReportNumberOfActiveInnboksEvents()
        }
    }

    private suspend fun runTotalEventsMetricsReporting() {
        applicationContext.totalEventsMetricsCollector.run {
            getAndReportEventsPerUser()
            getAndReportVisibleEventsPerUser()
            getAndReportActiveEventsPerUser()
            getAndReportEventActiveRatePerUser()
            getAndReportExpiredEventsPerUser()
            getAndReportEventExpiredRate()
            getAndReportExpiredEventPerUserByInvisible()
            getAndReportEventsPerGroupId()
            getAndReportGroupIdsPerUser()
            getAndReportEventTextLength()

            getAndReportNumberOfUsersWithEvents()
            getAndReportNumberOfEvents()
            getAndReportNumberOfVisibleEvents()
            getAndReportNumberOfActiveEvents()
            getAndReportNumberOfExpiredEvents()
        }
    }

    private suspend fun runDoneEventsMetricsReporting() {
        applicationContext.doneMetricsCollector.run {
            getAndReportNumberOfCachedDoneEvents()
        }
    }
}
