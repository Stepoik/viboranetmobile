plugins {
    id("compose-multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.coreUi)
            implementation(projects.uikit)
            implementation(projects.features.home.route)
            implementation(projects.features.home.chat.api)
            implementation(projects.features.home.chat.data)
            implementation(projects.features.common.api)
        }
    }
}

android {
    namespace = "stepan.gorokhov.viboranet.home.flow"
}