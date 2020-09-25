import org.gradle.api.tasks.testing.logging.TestLogEvent.*

buildscript {
    repositories {
        mavenLocal()
        jcenter()
        google()
        mavenCentral()
    }
}
plugins {
    java
    jacoco
    id("de.fayard.buildSrcVersions") version "0.7.0"
}

group = "com.acme"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

description = "Gilded Rose"

repositories {
    jcenter()
    mavenLocal()
    mavenCentral()
    //maven { url("http://repo.gradle.org/gradle/libs-releases-local") }
}

dependencies {
    testImplementation(Libs.junit_jupiter_api)
    testImplementation(Libs.junit_jupiter_params)
    testImplementation(Libs.hamcrest)

    testRuntime(Libs.junit_jupiter_engine)
}

jacoco {
    toolVersion = "0.8.5"
    reportsDir = file("$buildDir/jacocoReport")
}

tasks.jacocoTestReport {
    group = "Reporting"
    description = "Generate Jacoco coverage reports after running tests."
    reports {
        xml.isEnabled = true
        html.isEnabled = true
    }
}

tasks.test {
    extensions.configure(JacocoTaskExtension::class) {
        setDestinationFile(file("$buildDir/jacoco/jacocoTest.exec"))
        classDumpDir = file("$buildDir/jacoco/classpathdumps")
    }

    useJUnitPlatform()
    testLogging {
        events(PASSED, SKIPPED, FAILED)
    }
}

tasks.wrapper {
    gradleVersion = Versions.gradleLatestVersion
}
