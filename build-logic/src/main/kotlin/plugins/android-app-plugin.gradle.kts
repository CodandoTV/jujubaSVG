import config.Config
import ext.getVersionFromCatalogs

@Suppress("DSL_SCOPE_VIOLATION")
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
    compileSdk = Config.COMPILE_SDK

    defaultConfig {
        minSdk = Config.MIN_SDK
        targetSdk = Config.TARGET_SDK
        versionCode = Config.versionCode()
        versionName = Config.versionName()

        testInstrumentationRunner = Config.TEST_INSTRUMENTATION_RUNNER
        vectorDrawables.useSupportLibrary = true

        applicationId = Config.APPLICATION_ID

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

    namespace = Config.APPLICATION_ID

    kotlinOptions {
        jvmTarget = Config.JAVA_VM_TARGET
    }
}
