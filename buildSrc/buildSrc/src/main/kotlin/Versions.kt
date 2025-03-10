object Versions {
    object Android {
        const val version = "8.5.2"
        const val compileSdk = 34
        const val targetSdk = 34
        const val minSdk = 28
    }

    object Application {
        const val versionCode = 1
        const val versionName = "1.0"
    }

    object Kotlin {
        const val version = "2.1.10"

        object Coroutines {
            const val version = "1.9.0"
        }

        object Serialization {
            const val version = Kotlin.version
        }
    }

    object Compose {
        const val version = "1.7.1"

        object Compiler {
            const val version = Kotlin.version
        }

        object Collections {
            const val version = "0.3.8"
        }

        object ViewModel {
            const val version = "2.8.3"
        }

        object Navigation {
            const val version = "2.8.0-alpha09"
        }
    }

    object Koin {
        const val version = "4.0.0"
    }
}