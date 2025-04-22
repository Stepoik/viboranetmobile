plugins {
    id("multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.database)
            implementation(projects.features.auth.api)
            implementation(Dependencies.Firebase.auth)
        }
    }
}

android {
    namespace = "stepan.gorokhov.viboranet.auth.data"
}