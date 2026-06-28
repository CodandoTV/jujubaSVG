@file:Suppress("UnstableApiUsage")

plugins {
    id("plugins.android-app-plugin")
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.jetbrains.compose)
}

android {
    defaultConfig {
        applicationId = "com.github.codandotv.jujubasvg.sampleapp"
    }
    namespace = "com.github.codandotv.jujubasvg.sampleapp"
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.ui)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.compose.activity)

    api(projects.jujubasvg)
}
