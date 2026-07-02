plugins {
    id("plugins.kmp-library-plugin")
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.serialization)
}

compose.resources {
    publicResClass = false
    packageOfResClass = "com.github.codandotv.jujubasvg.sampleapp.resources"
    generateResClass = always
}

kotlin {
    android {
        namespace = "com.github.gabrielbmoro.jujubasvg.sampleapp"
    }

    listOf(iosArm64(), iosSimulatorArm64()).forEach { target ->
        target.binaries.framework {
            baseName = "sampleApp"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.ui)
            implementation(libs.material3)
            implementation(libs.compose.components.resources)
            implementation(projects.jujubasvg)
        }
    }
}
