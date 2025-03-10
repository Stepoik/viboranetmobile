plugins {
    id("compose-multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.coreUi)
        }
    }
}

android {
    namespace = "stepan.gorokhov.viboranet.common.presentation"
}