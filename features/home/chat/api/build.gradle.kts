plugins {
    id("multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(Dependencies.Kotlin.datetime)
        }
    }
}

android {
    namespace = "stepan.gorokhov.viboranet.chat.api"
}