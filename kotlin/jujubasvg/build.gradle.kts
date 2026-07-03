import com.vanniktech.maven.publish.SonatypeHost
import java.util.Properties

plugins {
    id("plugins.kmp-library-plugin")
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
        withHostTest {}
    }
    sourceSets {
        commonMain.dependencies {
            implementation(libs.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.material3)
            implementation(libs.compose.webview.multiplatform)
            implementation(libs.jetbrains.kotlinx.serialization.json)
            implementation(libs.kermit)
        }
        androidMain.dependencies {
            implementation(libs.ui.tooling)
            implementation(libs.ui.tooling.preview)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotlin.test.common)
            implementation(libs.kotlinx.coroutines.test)
        }
        getByName("androidHostTest").dependencies {
            implementation(libs.kotlin.test)
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
    if (System.getenv("ORG_GRADLE_PROJECT_signingInMemoryKeyId") != null) {
        signAllPublications()
    }

    coordinates(
        project.property("GROUP_ID") as String,
        project.property("ARTIFACT_ID") as String,
        versionPublish
    )

    pom {
        name.set(project.property("POM_NAME") as String)
        description.set(project.property("POM_DESCRIPTION") as String)
        inceptionYear.set(project.property("POM_INCEPTION_YEAR") as String)
        url.set(project.property("POM_URL") as String)

        licenses {
            license {
                name.set(project.property("POM_LICENSE_NAME") as String)
                url.set(project.property("POM_LICENSE_URL") as String)
            }
        }
        scm {
            connection.set(project.property("POM_SCM_CONNECTION") as String)
            url.set(project.property("POM_SCM_URL") as String)
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
