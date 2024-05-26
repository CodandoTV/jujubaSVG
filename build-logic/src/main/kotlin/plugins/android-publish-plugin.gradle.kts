import config.Config

plugins {
    id("com.android.library")
    id("maven-publish")
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
