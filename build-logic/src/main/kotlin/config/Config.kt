package config

import org.gradle.api.JavaVersion

object Config {
    const val applicationId = "com.gabrielbmoro.sample"
    const val groupId = "io.github.gabrielbmoro"
    const val artifactId = "jujubasvg"
    const val libVersion = "1.0.3"
    const val minSdk = 22
    const val targetSdk = 34
    const val compileSdk = 34
    private const val localVersionCode = 10
    private const val localVersionName = "1.0.0"
    val javaCompatibilityVersion = JavaVersion.VERSION_17
    const val javaVMTarget = "17"
    const val haveUnitTestsDefaultValues = true
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    fun versionCode(): Int {
        val versionCode = try {
            System.getenv("BITRISE_BUILD_NUMBER").toIntOrNull()
        } catch (nullPointerException: NullPointerException) {
            null
        }
        return versionCode ?: localVersionCode
    }

    fun versionName(): String {
        val versionName = try {
            "1.8.${System.getenv("BITRISE_BUILD_NUMBER")}"
        } catch (nullPointerException: NullPointerException) {
            null
        }

        return versionName?.ifEmpty { localVersionName } ?: localVersionName
    }
}