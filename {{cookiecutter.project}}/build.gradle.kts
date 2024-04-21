@file:Suppress("UnstableApiUsage")

import org.gradle.api.tasks.wrapper.Wrapper.DistributionType.ALL
import org.gradle.api.attributes.TestSuiteType.FUNCTIONAL_TEST

group = "{{cookiecutter.group}}"
version = "{{cookiecutter.version}}"

plugins {
    application
    `java-test-fixtures`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.slf4j)
    runtimeOnly(libs.logback)
}

testing {
    suites {
        withType<JvmTestSuite>().configureEach {
            useJUnitJupiter(libs.versions.junit)
            dependencies {
                implementation(libs.junit.params)
                implementation(libs.assertj)
            }
        }
        val test by getting(JvmTestSuite::class) {
            dependencies {
                implementation(libs.mockito)
            }
        }
        val testFunc by registering(JvmTestSuite::class) {
            testType = FUNCTIONAL_TEST
            dependencies {
                implementation(project())
            }
        }
    }
}

tasks.check {
    dependsOn(testing.suites.named("testFunc"))
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of({{cookiecutter.java_version}})
    }
}

application {
    mainClass = "{{cookiecutter.package}}.App"
}

tasks.wrapper {
    distributionType = ALL
    gradleVersion = "latest"
}
