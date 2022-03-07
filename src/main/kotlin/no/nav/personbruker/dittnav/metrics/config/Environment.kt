package no.nav.personbruker.dittnav.metrics.config

import no.nav.personbruker.dittnav.common.util.config.IntEnvVar
import no.nav.personbruker.dittnav.common.util.config.StringEnvVar.getEnvVar

data class Environment(val dbHost: String = getEnvVar("DB_NIGHTLYSTATS_HOST"),
                       val dbName: String = getEnvVar("DB_NIGHTLYSTATS_DATABASE"),
                       val dbUser: String = getEnvVar("DB_NIGHTLYSTATS_USERNAME"),
                       val dbPassword: String = getEnvVar("DB_NIGHTLYSTATS_PASSWORD"),
                       val dbPort: String = getEnvVar("DB_NIGHTLYSTATS_PORT"),
                       val dbUrl: String = getDbUrl(dbHost, dbPort, dbName),
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

fun getDbUrl(host: String, port: String, name: String): String {
    return if (host.endsWith(":$port")) {
        "jdbc:postgresql://${host}/$name"
    } else {
        "jdbc:postgresql://${host}:${port}/${name}"
    }
}
