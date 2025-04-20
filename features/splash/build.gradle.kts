plugins {
    id("compose-multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.coreUi)
            implementation(projects.features.common.presentation)
            implementation(projects.features.common.api)
        }
    }
}

android {
    namespace = "stepan.gorokhov.viboranet.splash"
}