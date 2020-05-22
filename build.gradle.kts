val vaultJdbcVersion = "1.3.1"
val flywayVersion = "5.2.4"
val hikariCPVersion = "3.2.0"
val postgresVersion = "42.2.5"
val coroutinesVersion = "1.3.6"
val influxdbVersion = "2.8"

plugins {
    val kotlinVersion = "1.3.71"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.allopen") version kotlinVersion

    application
}

application {
    mainClassName = "no.nav.personbruker.dittnav.metrics.metrics.MainKt"
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    compile("no.nav:vault-jdbc:$vaultJdbcVersion")
    compile("com.zaxxer:HikariCP:$hikariCPVersion")
    compile("org.postgresql:postgresql:$postgresVersion")
    compile("org.flywaydb:flyway-core:$flywayVersion")
    compile("org.influxdb:influxdb-java:$influxdbVersion")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }

    withType<Jar> {
        manifest {
            attributes["Main-Class"] = application.mainClassName
        }
        from(configurations.runtime.get().map { if (it.isDirectory) it else zipTree(it) })
    }
}