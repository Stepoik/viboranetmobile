plugins {
    id("compose-multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.coreUi)
            implementation(projects.uikit)
            implementation(projects.features.common.presentation)
            implementation(projects.features.tests.api)

            implementation(Dependencies.Coil.compose)
        }
    }
}

android {
    namespace = "stepan.gorokhov.viboranet.tests.presentation"
}