plugins {
    id("multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.coreUi)
            implementation(projects.features.auth.presentation)
            implementation(projects.features.common.presentation)
            implementation(projects.features.home.flow)
            implementation(projects.features.splash)
            implementation(projects.database)
            implementation(projects.coreData)
            implementation(Dependencies.Firebase.auth)
        }
    }
}

android {
    namespace = "stepan.gorokhov.viboranet.root"
}