@file:Suppress("UnstableApiUsage")

buildscript {
    repositories {
        mavenCentral()
        google()
    }

    dependencies {
        classpath(libs.gradle)
        classpath(libs.kotlin.gradle.plugin)
    }
}

tasks {
    withType<Copy> {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    register("clean", Delete::class) {
        delete(rootProject.layout.buildDirectory)
    }
}
