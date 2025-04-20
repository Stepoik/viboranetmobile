plugins {
    id("compose-multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.coreUi)
            implementation(projects.uikit)
            implementation(projects.features.common.presentation)
            implementation(projects.features.auth.api)
            implementation(projects.features.auth.domain)
            implementation(projects.features.auth.data)
        }
    }
}

android {
    namespace = "stepan.gorokhov.viboranet.auth.presentation"
}