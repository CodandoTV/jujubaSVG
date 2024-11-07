import config.Config

import org.gradle.kotlin.dsl.kotlin

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("detekt-plugin-setup")
}

kotlin {
    explicitApi()
}

android {
    compileSdk = Config.COMPILE_SDK

    defaultConfig {
        minSdk = Config.MIN_SDK
    }

    compileOptions {
        sourceCompatibility = Config.javaCompatibilityVersion
        targetCompatibility = Config.javaCompatibilityVersion
    }

    testOptions {
        unitTests.isReturnDefaultValues = Config.HAVE_UNIT_TESTS_DEFAULT_VALUES
    }

    kotlinOptions {
        jvmTarget = Config.JAVA_VM_TARGET
    }
}
