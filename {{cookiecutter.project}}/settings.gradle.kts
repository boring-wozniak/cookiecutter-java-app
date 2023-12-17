import java.lang.System.getenv as env

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "latest.release"
    id("com.gradle.enterprise") version "latest.release"
}

rootProject.name = "{{cookiecutter.project}}"

gradleEnterprise {
    buildScan {
        publishAlwaysIf(isRunningOnCi())
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
    }
}

fun isRunningOnCi() = env("CI") == "true"
