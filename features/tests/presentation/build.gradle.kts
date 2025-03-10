plugins {
    id("compose-multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.coreUi)
            implementation(projects.features.common.presentation)
            implementation(projects.features.tests.api)
        }
    }
}

android {
    namespace = "stepan.gorokhov.viboranet.tests.presentation"
}