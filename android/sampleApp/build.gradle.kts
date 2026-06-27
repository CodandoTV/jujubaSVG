@file:Suppress("UnstableApiUsage")

plugins {
    id("plugins.android-app-plugin")
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.jetbrains.compose)
    kotlin("android")
    kotlin("kapt")
}

dependencies {
    implementation(libs.ui)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.compose.activity)

    api(projects.jujubasvg)
}
