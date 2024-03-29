import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version Kotlin.version
    kotlin("plugin.allopen") version Kotlin.version
    id(Shadow.pluginId) version Shadow.version

    application
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

application {
    mainClass.set("no.nav.personbruker.dittnav.metrics.MainKt")
}

repositories {
    mavenCentral()
    maven("https://jitpack.io")
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
    implementation(DittNAV.Common.utils)
    implementation(DittNAV.Common.influxdb)
    implementation(Influxdb.java)
    implementation(Kotlinx.coroutines)
    implementation("ch.qos.logback:logback-classic:1.3.0-alpha14")
    implementation(Logstash.logbackEncoder)
    implementation(Tms.KtorTokenSupport.azureExchange)
    implementation(Ktor.clientApache)
    implementation(Ktor.clientJson)
    implementation(Ktor.clientJackson)
    implementation(Jackson.dataTypeJsr310)
    testImplementation(kotlin("test-junit5"))
    testImplementation(Junit.engine)
    testImplementation(Junit.api)
    testImplementation(Mockk.mockk)
    testImplementation(Kluent.kluent)
    testImplementation(Ktor.clientMock)

    intTestImplementation(Junit.engine)
}

tasks {
    withType<Test> {
        useJUnitPlatform()
        testLogging {
            exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
            events("passed", "skipped", "failed")
        }
    }

    register("runServer", JavaExec::class) {
        environment("SERVICEUSER_USERNAME", "username")
        environment("SERVICEUSER_PASSWORD", "password")
        environment("DB_HOST", "localhost:5432")
        environment("DB_NAME", "dittnav-event-cache-preprod")
        environment("DB_PASSWORD", "testpassword")
        environment("DB_MOUNT_PATH", "notUsedOnLocalhost")
        environment("CLUSTER_NAME", "dev-sbs")
        environment("SENSU_HOST", "stub")
        environment("SENSU_PORT", "0")
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

// TODO: Fjern følgende work around i ny versjon av Shadow-pluginet:
// Skal være løst i denne: https://github.com/johnrengelman/shadow/pull/612
project.setProperty("mainClassName", application.mainClass.get())
apply(plugin = Shadow.pluginId)
