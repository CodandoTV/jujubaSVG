@file:Suppress("UnstableApiUsage")

plugins {
    id("android-app-plugin")
}

dependencies {
    // Compose
    implementation(libs.bundles.compose.impl)
    debugImplementation(libs.bundles.compose.debug.impl)
    implementation(libs.bundles.compose.extras)

    api(projects.jujubasvg)
}
