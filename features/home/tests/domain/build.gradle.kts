plugins {
    id("multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.features.home.tests.api)
        }
    }
}

android {
    namespace = "stepan.gorokhov.viboranet.home.tests.domain"
}