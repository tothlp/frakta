import java.util.*

val kotlinVersion = "1.9.23"

plugins {
    kotlin("jvm") version "1.9.23"
    // needed for funcioning jar.
    id("com.github.johnrengelman.shadow") version "7.1.2"
    kotlin("plugin.serialization") version "1.9.22"
    id("io.ktor.plugin") version "2.3.10"
}

group = "hu.tothlp"
val appVersion = "0.1"
version = appVersion

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    implementation("com.github.ajalt.clikt:clikt:4.2.2")

    implementation("ch.qos.logback:logback-classic:1.4.14")
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
    implementation("io.ktor:ktor-server-content-negotiation-jvm")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
}


tasks.shadowJar {
    archiveBaseName.set("frakta")
    archiveVersion.set(appVersion)
    archiveClassifier.set("")
    dependsOn(generateVersionProperties)
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "hu.tothlp.MainKt"
    }
}

tasks.build {
    dependsOn(tasks.shadowJar)
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}

val generateVersionProperties by tasks.registering {
    doLast {
        val propertiesFile = file("${layout.buildDirectory.get().asFile}/resources/main/version.properties")
        propertiesFile.parentFile.mkdirs()
        val properties = Properties()
        properties["version"] = rootProject.version.toString()
        propertiesFile.writer().use { properties.store(it, null) }
    }
}

tasks.named("processResources") {
    dependsOn(generateVersionProperties)
}

