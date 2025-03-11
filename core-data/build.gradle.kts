plugins {
    id("multiplatform-setup")
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(Dependencies.Ktor.clientOkHttp)
        }
        commonMain.dependencies {
            api(Dependencies.Ktor.clientCore)
            api(Dependencies.Ktor.serialization)
            api(Dependencies.Ktor.contentNegotiation)

            api(Dependencies.Datastore.core)
        }
        iosMain.dependencies {
            implementation(Dependencies.Ktor.clientDarwin)
        }
    }
}

android {
    namespace = "stepan.gorokhov.viboranet.coredata"
}