package no.nav.personbruker.dittnav.metrics.database

import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.runBlocking
import java.io.File

class H2Database : Database {

    private val memDataSource: HikariDataSource

    init {
        memDataSource = createDataSource()
        createTablesAndData()
    }

    override val dataSource: HikariDataSource
        get() = memDataSource

    private fun createDataSource(): HikariDataSource {
        return HikariDataSource().apply {
            jdbcUrl = "jdbc:h2:mem:testdb;MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE"
            username = "sa"
            password = ""
            validate()
        }
    }

    private fun createTablesAndData() {
        executeFilesInDirectory("/db/tables/")

        executeFilesInDirectory("/db/data/")
    }

    private fun executeFilesInDirectory(dirName: String) {
        val directory = this::class.java.getResource(dirName).toURI()

        File(directory).listFiles()?.forEach { file ->
            executeQueryFile(file)
        }
    }

    private fun executeQueryFile(fileName: File) {
        runBlocking {
            val fileContent = fileName.readText()
            dbQuery { prepareStatement(fileContent).execute() }
        }
    }
}