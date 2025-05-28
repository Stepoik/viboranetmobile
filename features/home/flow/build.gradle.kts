plugins {
    id("compose-multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.coreUi)
            implementation(projects.uikit)
            implementation(projects.features.home.route)
            implementation(projects.features.home.tests.presentation)
            implementation(projects.features.home.chat.presentation)
            implementation(projects.features.home.profile.presentation)
            implementation(projects.features.home.leaderboard.presentation)
            implementation(projects.features.common.presentation)
        }
    }
}

android {
    namespace = "stepan.gorokhov.viboranet.home.chat.presentation"
}