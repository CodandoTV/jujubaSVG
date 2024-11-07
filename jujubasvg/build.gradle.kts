
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("android-compose-library-plugin")
    id("android-publish-plugin")
}

android {
    namespace = "com.github.gabrielbmoro.jujubasvg"

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    // Compose
    implementation(libs.bundles.compose.impl)
    debugImplementation(libs.bundles.compose.debug.impl)

    implementation(libs.bundles.compose.extras)

    testImplementation(kotlin("test"))
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
}
