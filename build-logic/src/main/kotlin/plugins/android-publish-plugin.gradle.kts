import config.Config

plugins {
    id("com.android.library")
}

android {
    defaultConfig {
        aarMetadata {
            minCompileSdk = Config.minSdk
        }
    }
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}
