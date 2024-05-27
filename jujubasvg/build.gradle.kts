@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("android-compose-library-plugin")
    id("android-publish-plugin")
}

android {
    namespace = "com.github.gabrielbmoro.jujubasvg"

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose.impl)
    debugImplementation(libs.bundles.compose.debug.impl)
    implementation(libs.bundles.compose.extras)
}