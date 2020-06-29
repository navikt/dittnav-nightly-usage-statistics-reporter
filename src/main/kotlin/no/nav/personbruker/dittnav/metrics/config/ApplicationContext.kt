package no.nav.personbruker.dittnav.metrics.config

import no.nav.personbruker.dittnav.metrics.beskjed.BeskjedMetricsCollector
import no.nav.personbruker.dittnav.metrics.done.DoneMetricsCollector
import no.nav.personbruker.dittnav.metrics.innboks.InnboksMetricsCollector
import no.nav.personbruker.dittnav.metrics.oppgave.OppgaveMetricsCollector
import no.nav.personbruker.dittnav.metrics.total.TotalEventsMetricsCollector

interface ApplicationContext {
    val beskjedMetricsCollector: BeskjedMetricsCollector
    val oppgaveMetricsCollector: OppgaveMetricsCollector
    val innboksMetricsCollector: InnboksMetricsCollector
    val totalEventsMetricsCollector: TotalEventsMetricsCollector
    val doneMetricsCollector: DoneMetricsCollector
}