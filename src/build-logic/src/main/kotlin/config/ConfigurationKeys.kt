package config

import model.JavaConfiguration
import model.SdkConfiguration
import org.gradle.api.JavaVersion

internal object ConfigurationKeys {

    const val APPLICATION_ID = "com.gabrielbmoro.jujuba"
    const val HAS_UNIT_TESTS_DEFAULT_VALUES = true

    val javaConfiguration = JavaConfiguration(
        javaVmTarget = "17",
        version = JavaVersion.VERSION_17
    )

    val sdkConfiguration = SdkConfiguration(
        minSdk = 22,
        targetSdk = 34,
        compileSdk = 34
    )
}
