package no.nav.personbruker.dittnav.metrics.config

import no.nav.personbruker.dittnav.metrics.collectors.BeskjedMetricsCollector
import no.nav.personbruker.dittnav.metrics.collectors.InnboksMetricsCollector
import no.nav.personbruker.dittnav.metrics.collectors.OppgaveMetricsCollector
import no.nav.personbruker.dittnav.metrics.collectors.TotalEventsMetricsCollector

interface ApplicationContext {
    val beskjedMetricsCollector: BeskjedMetricsCollector
    val oppgaveMetricsCollector: OppgaveMetricsCollector
    val innboksMetricsCollector: InnboksMetricsCollector
    val totalEventsMetricsCollector: TotalEventsMetricsCollector
}
