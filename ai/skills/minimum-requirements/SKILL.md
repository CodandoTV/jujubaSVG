---
name: minimum-requirements
description: Automatically determine minimum requirements for consuming the library
---

# minimum-requirements

## Goal

Automatically determine the minimum requirements needed to consume the library.

## Behavior

When invoked:

1. **Detect project type:**
   - Check for `kotlin/` directory with Gradle build files → Kotlin Multiplatform Library
   - Check for `flutter/` directory with `pubspec.yaml` → Flutter Package

2. **Inspect dependency declarations:**
   - `kotlin/gradle/libs.versions.toml` (version catalog)
   - `kotlin/gradle/wrapper/gradle-wrapper.properties` (Gradle version)
   - `kotlin/build-logic/src/main/kotlin/config/Config.kt` (minSdk, JDK target)
   - `flutter/jujuba_svg/pubspec.yaml` (Dart SDK, Flutter version)

3. **Determine minimum supported versions:**

   ### Kotlin Multiplatform
   - Kotlin version: from `libs.versions.toml` (`kotlin`)
   - JDK version: from `Config.kt` (`javaCompatibilityVersion`)
   - Gradle version: from `gradle-wrapper.properties` (`distributionUrl`)
   - Android Gradle Plugin version: from `libs.versions.toml` (`gradle`)
   - Compose Multiplatform version: from `libs.versions.toml` (`compose_plugin`)
   - Min SDK: from `Config.kt` (`MIN_SDK`)

   ### Flutter
   - Dart SDK constraint: from `pubspec.yaml` (`environment.sdk`)
   - Flutter dependency: implicit via `sdk: flutter`

4. **Create or update** a `## Minimum Requirements` section inside `README.md`.

### Example output

```md
## Minimum Requirements

### Kotlin Multiplatform Library

- Kotlin 2.4.0+
- JDK 17+
- Gradle 9.1.0+
- Compose Multiplatform 1.11.1+
- Android API 23+

### Flutter Package

- Dart ^3.5.0+
- Flutter (latest stable)
```
