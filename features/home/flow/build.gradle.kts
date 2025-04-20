plugins {
    id("compose-multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.coreUi)
            implementation(projects.uikit)
            implementation(projects.features.home.route)
            implementation(projects.features.common.presentation)
            implementation(projects.features.home.tests.presentation)
        }
    }
}

android {
    namespace = "stepan.gorokhov.viboranet.home.flow"
}