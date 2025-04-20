plugins {
    id("multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.database)
            implementation(projects.features.auth.api)
        }
    }
}

android {
    namespace = "stepan.gorokhov.viboranet.auth.data"
}