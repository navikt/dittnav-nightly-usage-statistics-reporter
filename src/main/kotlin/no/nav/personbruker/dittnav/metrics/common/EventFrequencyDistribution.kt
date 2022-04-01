package no.nav.personbruker.dittnav.metrics.common

data class NumberOfEventsFrequency(val antallEventer: Int, val antallBrukere: Int)

class EventFrequencyDistribution(val eventFrequencies: List<NumberOfEventsFrequency>) {
    fun getGroupedFrequencyDistribution(): Map<String, Int> {
        val groupedEventFrequencies = mutableMapOf<String, Int>()
        groupedEventFrequencies["10-14"] = 0
        groupedEventFrequencies["15-19"] = 0
        groupedEventFrequencies["20+"] = 0
        eventFrequencies.forEach {
            if (it.antallEventer < 10) groupedEventFrequencies[it.antallEventer.toString()] = it.antallBrukere
            if (it.antallEventer in 10 until 15)
                groupedEventFrequencies["10-14"] = groupedEventFrequencies["10-14"]!! + it.antallBrukere
            if (it.antallEventer in 15 until 20)
                groupedEventFrequencies["15-19"] = groupedEventFrequencies["15-19"]!! + it.antallBrukere
            if (it.antallEventer >= 20)
                groupedEventFrequencies["20+"] = groupedEventFrequencies["20+"]!! + it.antallBrukere
        }
        return groupedEventFrequencies
    }
}