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
   - Kotlin/Android: modules defined in `android/settings.gradle.kts` (`:jujubasvg`, `:sampleApp`)
   - Convention plugins: `android/build-logic/`
   - Flutter: package at `flutter/jujuba_svg/`, sample at `flutter/sample/`

2. **Analyze dependencies between modules/packages:**

   ### Kotlin / Android
   - `:sampleApp` depends on `:jujubasvg`
   - `:build-logic` provides convention plugins used by both
   - No circular dependencies between `:jujubasvg` and `:sampleApp`

   ### Flutter
   - `flutter/sample` depends on `flutter/jujuba_svg` (local path dependency)
   - No circular dependencies

3. **Verify architecture rules:**

   ### Kotlin
   - `:jujubasvg` should not depend on `:sampleApp`
   - Internal packages should not leak into public API surface
   - `explicitApi()` should be enforced (set in convention plugin)

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
