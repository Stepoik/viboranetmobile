plugins {
    id("multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.features.common.api)
//            implementation(projects.coreData)
        }
    }
}

android {
    namespace = "stepan.gorokhov.viboranet.common.data"
}