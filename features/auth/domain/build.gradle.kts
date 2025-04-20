plugins {
    id("multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.features.auth.api)
            api(projects.features.common.api)
        }
    }
}

android {
    namespace = "stepan.gorokhov.viboranet.auth.domain"
}