package no.nav.personbruker.dittnav.metrics.config

import no.nav.personbruker.dittnav.common.util.config.IntEnvVar
import no.nav.personbruker.dittnav.common.util.config.StringEnvVar.getEnvVar

data class Environment(val dbHost: String = getEnvVar("DB_HOST"),
                       val dbName: String = getEnvVar("DB_NAME"),
                       val dbAdmin: String = getEnvVar("DB_NAME") + "-admin",
                       val dbUser: String = getEnvVar("DB_NAME") + "-user",
                       val dbUrl: String = "jdbc:postgresql://$dbHost/$dbName",
                       val dbMountPath: String = getEnvVar("DB_MOUNT_PATH"),
                       val clusterName: String = getEnvVar("CLUSTER_NAME"),
                       val sensuHost: String = getEnvVar("SENSU_HOST"),
                       val sensuPort: Int = IntEnvVar.getEnvVarAsInt("SENSU_PORT")
)
