import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val vaultJdbcVersion = "1.3.1"
val flywayVersion = "5.2.4"
val hikariCPVersion = "3.2.0"
val postgresVersion = "42.2.5"
val coroutinesVersion = "1.3.6"
val influxdbVersion = "2.8"
val mockkVersion = "1.9.3"
val h2Version = "1.4.200"
val junitVersion = "5.4.1"
val logstashVersion = "5.2"
val logbackVersion = "1.2.3"


plugins {
    val kotlinVersion = "1.3.71"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.allopen") version kotlinVersion

    id("org.flywaydb.flyway") version("5.2.4")

    application
}

application {
    mainClassName = "no.nav.personbruker.dittnav.metrics.MainKt"
}

repositories {
    mavenCentral()
    mavenLocal()
}

sourceSets {
    create("intTest") {
        compileClasspath += sourceSets.main.get().output + sourceSets.test.get().output
        runtimeClasspath += sourceSets.main.get().output + sourceSets.test.get().output
    }
}

val intTestImplementation by configurations.getting {
    extendsFrom(configurations.testImplementation.get())
}
configurations["intTestRuntimeOnly"].extendsFrom(configurations.testRuntimeOnly.get())

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    compile("ch.qos.logback:logback-classic:$logbackVersion")
    compile("net.logstash.logback:logstash-logback-encoder:$logstashVersion")
    compile("no.nav:vault-jdbc:$vaultJdbcVersion")
    compile("com.zaxxer:HikariCP:$hikariCPVersion")
    compile("org.postgresql:postgresql:$postgresVersion")
    compile("org.flywaydb:flyway-core:$flywayVersion")
    compile("org.influxdb:influxdb-java:$influxdbVersion")
    testCompile("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntime("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    testCompile(kotlin("test-junit5"))
    testImplementation("com.h2database:h2:$h2Version")
    testImplementation("io.mockk:mockk:$mockkVersion")

    intTestImplementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

tasks {
    withType<Jar> {
        manifest {
            attributes["Main-Class"] = application.mainClassName
        }
        from(configurations.runtime.get().map { if (it.isDirectory) it else zipTree(it) })
    }

    withType<Test> {
        useJUnitPlatform()
        testLogging {
            exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
            events("passed", "skipped", "failed")
        }
    }

    "run" (JavaExec::class) {
        environment("SERVICEUSER_USERNAME", "username")
        environment("SERVICEUSER_PASSWORD", "password")
        environment("DB_HOST", "localhost:5432")
        environment("DB_NAME", "dittnav-event-cache-preprod")
        environment("DB_PASSWORD", "testpassword")
        environment("DB_MOUNT_PATH", "notUsedOnLocalhost")
        environment("NAIS_CLUSTER_NAME", "dev-sbs")
        environment("NAIS_NAMESPACE", "q1")
        environment("SENSU_HOST", "stub")
        environment("SENSU_PORT", "")
    }
}

val integrationTest = task<Test>("integrationTest") {
    description = "Runs integration tests."
    group = "verification"

    testClassesDirs = sourceSets["intTest"].output.classesDirs
    classpath = sourceSets["intTest"].runtimeClasspath
    shouldRunAfter("test")
}

tasks.check { dependsOn(integrationTest) }