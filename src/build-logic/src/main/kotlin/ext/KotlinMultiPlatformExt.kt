package ext

import config.ConfigurationKeys
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun KotlinMultiplatformExtension.configurePlatformTargets() {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = ConfigurationKeys.javaConfiguration.javaVmTarget
            }
        }
    }
}