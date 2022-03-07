package no.nav.personbruker.dittnav.metrics.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import no.nav.personbruker.dittnav.common.util.config.StringEnvVar.getEnvVar
import no.nav.personbruker.dittnav.metrics.config.ConfigUtil
import no.nav.personbruker.dittnav.metrics.config.Environment
import no.nav.vault.jdbc.hikaricp.HikariCPVaultUtil

class PostgresDatabase(env: Environment) : Database {

    private val envDataSource: HikariDataSource

    init {
        envDataSource = createConnectionForLocalDbWithDbUser(env)
    }

    override val dataSource: HikariDataSource
        get() = envDataSource

    private fun createConnectionForLocalDbWithDbUser(env: Environment): HikariDataSource {
        return hikariFromLocalDb(env)
    }

    companion object {

        fun hikariFromLocalDb(env: Environment): HikariDataSource {
            val config = hikariCommonConfig(env)
            config.validate()
            return HikariDataSource(config)
        }

        private fun hikariCommonConfig(env: Environment): HikariConfig {
            val config = HikariConfig()
            config.driverClassName = "org.postgresql.Driver"
            config.jdbcUrl = env.dbUrl
            config.minimumIdle = 0
            config.maxLifetime = 1800000
            config.maximumPoolSize = 20
            config.connectionTimeout = 3000
            config.validationTimeout = 500
            config.idleTimeout = 30000
            config.isAutoCommit = false
            config.isReadOnly = true
            config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            config.username = env.dbUser
            config.password = env.dbPassword
            return config
        }
    }
}
