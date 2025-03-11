plugins {
    id("multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.coreData)
            implementation(projects.features.tests.api)
        }
    }
}

android {
    namespace = "stepan.gorokhov.viboranet.tests.data"
}