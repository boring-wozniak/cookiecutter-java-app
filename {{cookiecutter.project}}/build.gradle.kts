@file:Suppress("UnstableApiUsage")

import org.gradle.api.attributes.TestSuiteType.FUNCTIONAL_TEST


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
            targets {
                all {
                    testTask.configure {
                        systemProperty(
                            "junit.jupiter.displayname.generator.default",
                            "org.junit.jupiter.api.DisplayNameGenerator\$ReplaceUnderscores"
                        )
                    }
                }
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
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "{{cookiecutter.package}}.App"
}
