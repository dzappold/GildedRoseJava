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
    testImplementation(Libs.mockito_core)
    testImplementation(Libs.mockito_junit_jupiter)
    testImplementation(Libs.junit_quickcheck_core)
    testImplementation(Libs.junit_quickcheck_generators)
    testImplementation(Libs.junit_quickcheck_guava)
    testImplementation(Libs.junit_quickcheck)
    testImplementation(Libs.pitest)
    testImplementation(Libs.pitest_command_line)

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
        destinationFile = file("$buildDir/jacoco/jacocoTest.exec")
        classDumpDir = file("$buildDir/jacoco/classpathdumps")
    }
}

tasks.wrapper {
    gradleVersion = Versions.gradleLatestVersion
}
