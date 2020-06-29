package no.nav.personbruker.dittnav.metrics.config

object ConfigUtil {

    fun isCurrentlyRunningInCluster(): Boolean {
        return System.getenv("PUBLIC_APP_NAME") != null
    }

}