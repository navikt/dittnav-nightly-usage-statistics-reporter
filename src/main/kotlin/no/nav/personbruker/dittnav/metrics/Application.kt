package no.nav.personbruker.dittnav.metrics

import no.nav.personbruker.dittnav.metrics.config.ApplicationContext

class Application (val applicationContext: ApplicationContext = ApplicationContext()) {
    suspend fun runMetricsReporting() {
        test()
//        runBeskjedMetricsReporting()
//        runOppgaveMetricsReporting()
//        runInnboksMetricsReporting()
//        runTotalEventsMetricsReporting()
//        runDoneEventsMetricsReporting()
    }
    
    private suspend fun runBeskjedMetricsReporting() {
        applicationContext.beskjedMetricsCollector.run {
            getAndReportBeskjedEventsPerUser()
            getAndReportVisibleBeskjedEventsPerUser()
            getAndReportActiveBeskjedEventsPerUser()
            getAndReportBeskjedEventActiveRate()
            getAndReportExpiredBeskjedEventsPerUser()
            getAndReportBeskjedEventExpiredRate()
            getAndReportExpiredBeskjedEventPerUserByInvisible()
            getAndReportBeskjedEventsPerGroupId()
            getAndReportBeskjedGroupIdsPerUser()
            
            getAndReportNumberOfUsersWithBeskjedEvents()
            getAndReportNumberOfBeskjedEvents()
            getAndReportNumberOfVisibleBeskjedEvents()
            getAndReportNumberOfActiveBeskjedEvents()
            getAndReportNumberOfExpiredBeskjedEvents()
        }
    }    
    
    private suspend fun runOppgaveMetricsReporting() {
        applicationContext.oppgaveMetricsCollector.run {
            getAndReportOppgaveEventsPerUser()
            getAndReportVisibleOppgaveEventsPerUser()
            getAndReportActiveOppgaveEventsPerUser()
            getAndReportOppgaveEventsPerGroupId()
            getAndReportOppgaveGroupIdsPerUser()
            
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
            getAndReportInnboksEventsPerGroupId()
            getAndReportInnboksGroupIdsPerUser()

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
            getAndReportEventActiveRate()
            getAndReportExpiredEventsPerUser()
            getAndReportEventExpiredRate()
            getAndReportExpiredEventPerUserByInvisible()
            getAndReportEventsPerGroupId()
            getAndReportGroupIdsPerUser()

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

    private suspend fun test() {
        applicationContext.beskjedMetricsCollector.run {
            getAndReportNumberOfBeskjedEvents()
        }
    }
}