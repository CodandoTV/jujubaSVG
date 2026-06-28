package config

import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

@Suppress("TooGenericExceptionCaught", "SwallowedException")
object Config {
    const val MIN_SDK = 23
    const val TARGET_SDK = 37
    const val COMPILE_SDK = 37
    val javaCompatibilityVersion = JavaVersion.VERSION_17
    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
}
