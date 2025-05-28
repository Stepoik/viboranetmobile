plugins {
    id("multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.coreData)
            implementation(projects.features.home.leaderboard.api)
        }
    }
}

android {
    namespace = "stepan.gorokhov.viboranet.leaderboard.data"
}