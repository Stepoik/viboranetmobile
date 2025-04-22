import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id(Plugins.Kotlin.multiplatform)
    id(Plugins.Android.application)
    id(Plugins.Compose.multiplatform)
    id(Plugins.Compose.compiler)
    id(Plugins.Google.gms)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(projects.uikit)
            implementation(projects.features.common.presentation)
            implementation(projects.root)
            implementation(Dependencies.Compose.navigation)
            implementation(Dependencies.Koin.core)
            //Features
            implementation(projects.features.splash)
            implementation(projects.features.auth.presentation)
            implementation(projects.features.home.flow)
            //Firebase
            implementation(Dependencies.Firebase.auth)
        }
    }
}

android {
    namespace = "stepan.gorokhov.viboranet"
    compileSdk = Versions.Android.compileSdk

    defaultConfig {
        applicationId = "stepan.gorokhov.viboranet"
        minSdk = Versions.Android.minSdk
        targetSdk = Versions.Android.targetSdk
        versionCode = Versions.Application.versionCode
        versionName = Versions.Application.versionName
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

