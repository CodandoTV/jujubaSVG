package config

import org.gradle.api.JavaVersion

@Suppress("TooGenericExceptionCaught", "SwallowedException")
object Config {
    const val APPLICATION_ID = "com.codandotv.sample"
    const val MIN_SDK = 22
    const val TARGET_SDK = 34
    const val COMPILE_SDK = 34
    val javaCompatibilityVersion = JavaVersion.VERSION_17
    const val JAVA_VM_TARGET = "17"
    const val HAVE_UNIT_TESTS_DEFAULT_VALUES = true
    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
}
