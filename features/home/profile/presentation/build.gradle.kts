plugins {
    id("compose-multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.coreUi)
            implementation(projects.uikit)
            implementation(projects.features.home.route)
            implementation(projects.features.home.profile.api)
            implementation(projects.features.home.profile.data)
            implementation(projects.features.common.api)
            implementation(projects.features.home.tests.api)
            implementation(projects.features.home.tests.presentation)

            implementation(Dependencies.Coil.compose)
            implementation(Dependencies.Coil.ktor)
        }
    }
}

android {
    namespace = "stepan.gorokhov.viboranet.home.profile.presentation"
}