package plugins

import config.Config

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.kotlin.multiplatform.library")
}

kotlin {
    android {
        compileSdk = Config.COMPILE_SDK
        minSdk = Config.MIN_SDK
        androidResources.enable = true
    }
}
