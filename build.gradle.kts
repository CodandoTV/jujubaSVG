@file:Suppress("UnstableApiUsage")

plugins {
    id("maven-publish")
}
buildscript {
    repositories {
        maven(url= "https://jitpack.io")
        mavenCentral()
        google()
    }

    dependencies {
        classpath(libs.gradle)
        classpath(libs.kotlin.gradle.plugin)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}
