rootProject.name = "ViboraNet"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

include(":composeApp")

include(":features:auth:api")
include(":features:auth:data")
include(":features:auth:presentation")

include(":features:tests:api")
include(":features:tests:data")
include(":features:tests:presentation")

include(":features:splash")

include(":features:common:api")
include(":features:common:data")
include(":features:common:presentation")

include(":core-data")
include(":core")
include(":core-ui")
include(":uikit")
include(":root")