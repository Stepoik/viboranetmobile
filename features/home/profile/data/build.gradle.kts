plugins {
    id("multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.coreData)
            api(projects.features.home.profile.api)
            implementation(Dependencies.Firebase.auth)
        }
    }
}

android {
    namespace = "stepan.gorokhov.viboranet.chat.data"
}