@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("android-compose-library-plugin")
    id("maven-publish")
}

group = "com.github.gabrielbmoro"
version = "0.1.0"

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

afterEvaluate {
    publishing {
        repositories {
            maven {
                name = "JitPack"
                url = uri("https://jitpack.io")
            }
        }
        publications {
            create<MavenPublication>("release") {
                groupId = "com.github.gabrielbmoro"
                artifactId = "jujubasvg"
                version = "0.1.0"

                from(components["release"])
            }
        }
    }
}