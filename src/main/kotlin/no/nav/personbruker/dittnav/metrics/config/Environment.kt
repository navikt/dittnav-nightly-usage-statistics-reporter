package no.nav.personbruker.dittnav.metrics.config

import no.nav.personbruker.dittnav.common.util.config.IntEnvVar
import no.nav.personbruker.dittnav.common.util.config.StringEnvVar.getEnvVar

data class Environment(val dbHost: String = "N/A",
                       val dbName: String = "N/A",
                       val dbAdmin: String = "N/A",
                       val dbUser: String = "N/A",
                       val dbUrl: String = "N/A",
                       val dbMountPath: String = "N/A",
    val clusterName: String = getEnvVar("NAIS_CLUSTER_NAME"),
    val namespace: String = getEnvVar("NAIS_NAMESPACE"),
    val appnavn: String = getEnvVar("NAIS_APP_NAME"),
    val influxdbHost: String = getEnvVar("INFLUXDB_HOST"),
    val influxdbPort: Int = IntEnvVar.getEnvVarAsInt("INFLUXDB_PORT"),
    val influxdbName: String = getEnvVar("INFLUXDB_DATABASE_NAME"),
    val influxdbUser: String = getEnvVar("INFLUXDB_USER"),
    val influxdbPassword: String = getEnvVar("INFLUXDB_PASSWORD"),
    val influxdbRetentionPolicy: String = getEnvVar("INFLUXDB_RETENTION_POLICY"),
    val eventHandlerURL: String = getEnvVar("EVENT_HANDLER_URL"),
    val eventHandlerAppEnvironmentDetails: String = getEnvVar("HANDLER_ENVIRONMENT_DETAILS")
)
