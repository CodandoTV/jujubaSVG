@file:Suppress("UnstableApiUsage")

plugins {
    id("android-app-plugin")
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.jetbrains.compose)
    kotlin("android")
    kotlin("kapt")
}

dependencies {
    implementation(compose.ui)
    implementation(compose.preview)
    implementation(compose.material3)
    implementation(libs.compose.activity)

    api(projects.jujubasvg)
}
