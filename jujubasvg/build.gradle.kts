
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("android-library-plugin")
    id("android-publish-plugin")
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.jetbrains.compose)
}

android {
    namespace = "com.github.gabrielbmoro.jujubasvg"

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(compose.ui)
    implementation(compose.preview)
    implementation(compose.material3)
    implementation(libs.compose.activity)

    testImplementation(kotlin("test"))
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
    debugImplementation(compose.uiTooling)
}
