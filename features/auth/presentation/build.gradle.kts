plugins {
    id("compose-multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.coreUi)
            implementation(projects.features.common.presentation)
        }
    }
}

android {
    namespace = "stepan.gorokhov.viboranet.auth.presentation"
}