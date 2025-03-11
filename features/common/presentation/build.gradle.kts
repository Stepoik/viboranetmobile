plugins {
    id("compose-multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.coreUi)
        }
    }
}

android {
    namespace = "stepan.gorokhov.viboranet.common.presentation"
}