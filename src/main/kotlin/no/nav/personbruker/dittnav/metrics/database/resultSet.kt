package no.nav.personbruker.dittnav.metrics.database

import java.sql.ResultSet
import java.sql.SQLException

fun <T> ResultSet.mapSingleResult(resultMapper: ResultSet.() -> T): T =
    if (next()) {
        resultMapper()
    } else {
        throw SQLException("Found no rows")
    }