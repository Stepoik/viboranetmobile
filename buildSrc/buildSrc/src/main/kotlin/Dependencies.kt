object Dependencies {
    object Android {
        object Library {
            const val gradlePlugin =
                "com.android.library:com.android.library.gradle.plugin:${Versions.Android.version}"
        }
    }

    object Kotlin {
        const val gradlePlugin =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Kotlin.version}"

        const val coroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.Coroutines.version}"

        object Serialization {
            const val gradlePlugin = "org.jetbrains.kotlin:kotlin-serialization:${Versions.Kotlin.Serialization.version}"
        }
    }

    object Compose {
        const val gradlePlugin =
            "org.jetbrains.compose:compose-gradle-plugin:${Versions.Compose.version}"

        const val immutableCollections = "org.jetbrains.kotlinx:kotlinx-collections-immutable:${Versions.Compose.Collections.version}"
        const val viewModel = "org.jetbrains.androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.Compose.ViewModel.version}"
        const val navigation = "org.jetbrains.androidx.navigation:navigation-compose:${Versions.Compose.Navigation.version}"

        object Compiler {
            const val gradlePlugin =
                "org.jetbrains.kotlin:compose-compiler-gradle-plugin:${Versions.Compose.Compiler.version}"
        }
    }

    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.Koin.version}"
        const val koinCompose = "io.insert-koin:koin-compose:${Versions.Koin.version}"
        const val viewModel = "io.insert-koin:koin-compose-viewmodel:${Versions.Koin.version}"
    }
}