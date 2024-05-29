plugins {
    id("com.android.library")
    id("maven-publish")
    id("com.vanniktech.maven.publish")
}

mavenPublishing {
    signAllPublications()

    pom {
        name.set("JujubaSVG")
        description.set("A small library to help you handle SVG files on Android.")
        inceptionYear.set("2024")
        url.set("https://github.com/gabrielbmoro/jujubaSVG")
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        developers {
            developer {
                id.set("gabrielbmoro")
                name.set("gabrielbronzattimoro.es@gmail.com")
                url.set("https://github.com/gabrielbmoro/")
            }
        }
        scm {
            url.set("https://github.com/gabrielbmoro")
            connection.set("scm:git:git://github.com/gabrielbmoro/jujubaSVG.git")
            developerConnection.set("scm:git:ssh://git@github.com/gabrielbmoro/jujubaSVG.git")
        }
    }
}