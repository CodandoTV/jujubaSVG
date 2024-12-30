import config.Config

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.android.application")
    kotlin("android")
}

kotlin {
    explicitApi()
}

android {
    compileSdk = Config.COMPILE_SDK

    defaultConfig {
        minSdk = Config.MIN_SDK
        targetSdk = Config.TARGET_SDK
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = Config.TEST_INSTRUMENTATION_RUNNER
        vectorDrawables.useSupportLibrary = true

        applicationId = Config.APPLICATION_ID

        vectorDrawables.useSupportLibrary = true

        multiDexEnabled = true
    }

    @Suppress("UnstableApiUsage")
    testOptions {
        unitTests.isReturnDefaultValues = true
    }

    compileOptions {
        sourceCompatibility = Config.javaCompatibilityVersion
        targetCompatibility = Config.javaCompatibilityVersion
    }

    buildFeatures.compose = true

    namespace = Config.APPLICATION_ID

    kotlinOptions {
        jvmTarget = Config.JAVA_VM_TARGET
    }
}
