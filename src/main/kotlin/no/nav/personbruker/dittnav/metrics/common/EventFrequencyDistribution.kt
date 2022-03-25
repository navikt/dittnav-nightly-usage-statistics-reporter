package no.nav.personbruker.dittnav.metrics.common

data class NumberOfEventsFrequency(val antallEventer: Int, val antallBrukere: Int)

class EventFrequencyDistribution(val eventFrequencies: List<NumberOfEventsFrequency>) {
    fun getGroupedFrequencyDistribution(): Map<String, Int> {
        val groupedEventFrequencies = mutableMapOf<String, Int>()
        groupedEventFrequencies["10-15"] = 0
        eventFrequencies.forEach{
            if (it.antallEventer < 10) groupedEventFrequencies[it.antallEventer.toString()] = it.antallBrukere
            if (it.antallEventer in 10..15)
                groupedEventFrequencies["10-15"] = groupedEventFrequencies["10-15"]!! + it.antallBrukere
        }
        return groupedEventFrequencies
    }
}