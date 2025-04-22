plugins {
    id("multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.features.common.api)
            implementation(projects.database)
            implementation(Dependencies.Firebase.auth)
//            implementation(projects.coreData)
        }
    }
}

android {
    namespace = "stepan.gorokhov.viboranet.common.data"
}