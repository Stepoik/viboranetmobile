plugins {
    id("multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.coreData)
            api(projects.features.home.chat.api)
        }
    }
}

android {
    namespace = "stepan.gorokhov.viboranet.chat.data"
}