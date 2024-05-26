
plugins {
    id("com.android.library")
    id("maven-publish")
}
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
            artifactId = "jujubaSVG"

            from(components["release"])
        }
    }
}