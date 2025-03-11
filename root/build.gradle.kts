plugins {
    id("multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.features.auth.presentation)
            implementation(projects.features.tests.presentation)
            implementation(projects.features.splash)
            implementation(projects.coreData)
        }
    }
}

android {
    namespace = "stepan.gorokhov.viboranet.root"
}