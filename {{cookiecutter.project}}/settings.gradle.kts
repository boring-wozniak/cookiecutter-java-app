import java.lang.System.getenv as env

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "latest.release"
    id("com.gradle.develocity") version "latest.release"
}

rootProject.name = "{{cookiecutter.project}}"

develocity {
    buildScan {
        publishing.onlyIf { env("CI") == "true" }
        termsOfUseUrl = "https://gradle.com/help/legal-terms-of-use"
        termsOfUseAgree = "yes"
    }
}
