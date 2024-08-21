import config.Config
import ext.getVersionFromCatalogs

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("detekt-plugin-setup")
}

kotlin {
    explicitApi()
}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode()
        versionName = Config.versionName()

        testInstrumentationRunner = Config.testInstrumentationRunner
        vectorDrawables.useSupportLibrary = true

        applicationId = Config.applicationId

        vectorDrawables.useSupportLibrary = true

        multiDexEnabled = true
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }

    compileOptions {
        sourceCompatibility = Config.javaCompatibilityVersion
        targetCompatibility = Config.javaCompatibilityVersion
    }

    composeOptions {
        kotlinCompilerExtensionVersion = getVersionFromCatalogs("compose.compiler")
    }
    buildFeatures.compose = true

    namespace = Config.applicationId

    kotlinOptions {
        jvmTarget = Config.javaVMTarget
    }
}