@file:Suppress("UnstableApiUsage")

package plugins

import config.Config

import org.gradle.kotlin.dsl.kotlin

plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-parcelize")
}

kotlin {
    explicitApi()

    compilerOptions {
        jvmTarget.set(Config.JAVA_VM_TARGET)
    }
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
}
