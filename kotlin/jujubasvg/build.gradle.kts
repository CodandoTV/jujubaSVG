import com.vanniktech.maven.publish.SonatypeHost
import java.util.Properties

plugins {
    id("plugins.kmp-library-plugin")
    id("maven-publish")
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.vanniktech.maven.publish)
    alias(libs.plugins.serialization)
}

compose.resources {
    publicResClass = false
    packageOfResClass = "com.github.codandotv.jujubasvg.resources"
    generateResClass = always
}

kotlin {
    android {
        namespace = "com.github.gabrielbmoro.jujubasvg"
    }
    sourceSets {
        commonMain.dependencies {
            implementation(libs.ui)
            implementation(libs.ui.tooling)
            implementation(libs.ui.tooling.preview)
            implementation(libs.compose.components.resources)
            implementation(libs.material3)
            implementation(libs.compose.activity)
            implementation(libs.compose.webview.multiplatform)
            implementation(libs.jetbrains.kotlinx.serialization.json)
            implementation(libs.kermit)
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.kotlinx.coroutines.test)
        }
    }
}

val versionPropertiesFile = file("../jujubasvg/version.properties")
val versionProperties = Properties().apply {
    load(versionPropertiesFile.inputStream())
}

val versionPublish: String = versionProperties.getProperty("VERSION")

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()

    coordinates(
        project.property("GROUP_ID") as String,
        project.property("ARTIFACT_ID") as String,
        versionPublish
    )

    pom {
        name.set(project.property("ARTIFACT_ID") as String)
        description.set(project.property("ARTIFACT_ID") as String)
        inceptionYear.set("2024")
        url.set(project.property("POM_URL") as String)

        licenses {
            license {
                name.set(project.property("POM_LICENSE_NAME") as String)
                url.set(project.property("POM_LICENSE_URL") as String)
            }
        }
        scm {
            connection.set("scm:git@github.com:CodandoTV/jujubaSVG.git")
            url.set("https://github.com/CodandoTV/jujubaSVG.git")
        }
        developers {
            developer {
                id.set(project.property("POM_DEVELOPER_ID") as String)
                name.set(project.property("POM_DEVELOPER_NAME") as String)
                email.set(project.property("POM_DEVELOPER_EMAIL") as String)
            }
        }
    }
}
