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
   - Check for `android/` directory with Gradle build files → Kotlin Library
   - Check for `flutter/` directory with `pubspec.yaml` → Flutter Package

2. **Inspect dependency declarations:**
   - `android/gradle/libs.versions.toml` (version catalog)
   - `android/gradle/wrapper/gradle-wrapper.properties` (Gradle version)
   - `android/build-logic/src/main/kotlin/config/Config.kt` (minSdk, JDK target)
   - `flutter/jujuba_svg/pubspec.yaml` (Dart SDK, Flutter version)

3. **Determine minimum supported versions:**

   ### Kotlin / Android
   - Kotlin version: from `libs.versions.toml` (`kotlin`)
   - JDK version: from `Config.kt` (`javaCompatibilityVersion`)
   - Gradle version: from `gradle-wrapper.properties` (`distributionUrl`)
   - Android Gradle Plugin version: from `libs.versions.toml` (`gradle`)
   - Min SDK: from `Config.kt` (`MIN_SDK`)

   ### Flutter
   - Dart SDK constraint: from `pubspec.yaml` (`environment.sdk`)
   - Flutter dependency: implicit via `sdk: flutter`

4. **Create or update** a `## Minimum Requirements` section inside `README.md`.

### Example output

```md
## Minimum Requirements

### Kotlin Library

- Kotlin 2.1.0+
- JDK 17+
- Gradle 9.0+
- Android Gradle Plugin 8.13.1+
- Android API 22+

### Flutter Package

- Dart ^3.5.0+
- Flutter (latest stable)
```
