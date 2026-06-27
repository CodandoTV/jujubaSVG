package plugins

import config.Config

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.multiplatform")
}

kotlin {
    androidTarget()
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
}
