@file:Suppress("UnstableApiUsage")

plugins {
    id("kmp-app-plugin")
    alias(libs.plugins.jetbrains.compose)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.uiToolingPreview)
            implementation(projects.jujubasvg)
        }
    }
}