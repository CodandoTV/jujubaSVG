package ext

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension

internal fun BaseAppModuleExtension.configureBuildTypes() {
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
}