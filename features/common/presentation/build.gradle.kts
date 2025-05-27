plugins {
    id("compose-multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.coreUi)
            api(projects.features.common.api)
            api(projects.database)
            implementation(projects.features.common.data)

            implementation(Dependencies.Firebase.auth)
        }
    }
}

android {
    namespace = "stepan.gorokhov.viboranet.common.presentation"
}