plugins {
    id("compose-multiplatform-setup")
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(Dependencies.Ktor.clientOkHttp)
        }

        commonMain.dependencies {
            implementation(projects.coreUi)
            implementation(projects.uikit)
            implementation(projects.features.home.route)
            implementation(projects.features.home.leaderboard.data)
            implementation(projects.features.home.leaderboard.api)

            implementation(projects.features.common.api)

            implementation(Dependencies.Coil.compose)
            implementation(Dependencies.Coil.ktor)
        }
    }
}

android {
    namespace = "stepan.gorokhov.viboranet.home.leaderboard.presentation"
}