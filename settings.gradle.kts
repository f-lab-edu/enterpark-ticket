plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

rootProject.name = "enterpark-ticket"

include(
    "global-utils",
    "enterpark-ticket-admin",
    "enterpark-ticket-apis",
    "enterpark-ticket-apis:creator",
    "enterpark-ticket-apis:enduser",
    "enterpark-ticket-batch",
    "enterpark-ticket-core",
    "enterpark-ticket-core:domain",
)
