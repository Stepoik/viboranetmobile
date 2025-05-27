plugins {
    id("compose-multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.core)
            api(projects.coreData)
            implementation(Dependencies.Coil.ktor)
            implementation(Dependencies.Coil.compose)
        }
    }
}

android {
    namespace = "stepan.gorokhov.viboranet.coreui"
}