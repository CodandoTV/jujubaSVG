plugins {
    id("maven-publish")
    id("com.android.library")
}

android {
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}