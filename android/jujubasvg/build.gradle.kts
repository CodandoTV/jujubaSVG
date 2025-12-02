import com.vanniktech.maven.publish.SonatypeHost
import java.util.Properties

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("android-library-plugin")
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.jetbrains.compose)
    id("maven-publish")
    alias(libs.plugins.vanniktech.maven.publish)
}

android {
    namespace = "com.github.gabrielbmoro.jujubasvg"

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(compose.ui)
    implementation(compose.preview)
    implementation(compose.material3)
    implementation(libs.compose.activity)

    testImplementation(kotlin("test"))
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
    debugImplementation(compose.uiTooling)
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
