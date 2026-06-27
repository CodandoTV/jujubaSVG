package config

import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

@Suppress("TooGenericExceptionCaught", "SwallowedException")
object Config {
    const val APPLICATION_ID = "com.codandotv.sample"
    const val MIN_SDK = 23
    const val TARGET_SDK = 36
    const val COMPILE_SDK = 36
    val javaCompatibilityVersion = JavaVersion.VERSION_17
    val JAVA_VM_TARGET = JvmTarget.JVM_17
    const val HAVE_UNIT_TESTS_DEFAULT_VALUES = true
    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
}
