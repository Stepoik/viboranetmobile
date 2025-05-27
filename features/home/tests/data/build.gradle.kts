plugins {
    id("multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.coreData)
            implementation(projects.features.home.tests.api)
        }
    }
}

android {
    namespace = "stepan.gorokhov.viboranet.tests.data"
}