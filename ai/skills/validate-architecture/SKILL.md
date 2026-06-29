---
name: validate-architecture
description: Verify architectural consistency and dependency rules
---

# validate-architecture

## Goal

Verify architectural consistency and dependency rules.

## Behavior

When invoked:

1. **Detect project structure:**
   - Kotlin/KMP: modules defined in `kotlin/settings.gradle.kts` (`:jujubasvg`, `:androidSampleApp`)
   - Convention plugins: `kotlin/build-logic/`
   - Source sets: `commonMain`, `androidMain`, `commonTest` within `:jujubasvg`
   - Flutter: package at `flutter/jujuba_svg/`, sample at `flutter/sample/`

2. **Analyze dependencies between modules/packages:**

   ### Kotlin / KMP
   - `:androidSampleApp` depends on `:jujubasvg`
   - `:build-logic` provides convention plugins (`kmp-library-plugin`, `android-app-plugin`) used by both
   - No circular dependencies between `:jujubasvg` and `:androidSampleApp`
   - `commonMain` code must not depend on Android-specific APIs

   ### Flutter
   - `flutter/sample` depends on `flutter/jujuba_svg` (local path dependency)
   - No circular dependencies

3. **Verify architecture rules:**

   ### Kotlin / KMP
   - `:jujubasvg` should not depend on `:androidSampleApp`
   - Internal packages should not leak into public API surface
   - `explicitApi()` should be enforced (set in convention plugin)
   - `commonMain` should not reference Android SDK types (use `expect`/`actual`)
   - Android-specific implementations should live in `androidMain`

   ### Gradle Plugins
   - Convention plugins should only expose necessary configuration
   - Build-logic should not leak into library module classpath

   ### Flutter
   - `lib/core/`, `lib/model/`, `lib/util/` should respect layer separation
   - Core logic should not depend on UI widgets

4. **Detect:**

   - **Cyclic dependencies** — any module depending on one that depends back on it
   - **Forbidden dependencies** — e.g. sample app depending on internal build-logic
   - **Unused modules** — modules declared in settings but never referenced
   - **Architectural violations** — e.g. Compose UI leaking into pure Kotlin logic

5. **Produce a report:**

   ### Violations
   Critical issues (cyclic deps, forbidden deps).

   ### Warnings
   Potential issues (unused modules, unclear boundaries).

   ### Recommendations
   Suggested improvements (extract shared code, enforce layers).
