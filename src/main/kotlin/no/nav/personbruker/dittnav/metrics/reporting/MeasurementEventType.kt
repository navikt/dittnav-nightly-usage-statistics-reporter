package no.nav.personbruker.dittnav.metrics.reporting

// This class represents the scope of the recorded measurements, and is therefore distinct from EventType
// This allows us to have an ANY 'type' that exists as a measurement over all event types.
enum class MeasurementEventType {
    BESKJED, OPPGAVE, INNBOKS, DONE, ANY;
}