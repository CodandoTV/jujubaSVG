---
name: documentation-review
description: Validate project documentation against actual implementation
---

# documentation-review

## Goal

Validate project documentation and ensure it matches the actual implementation.

## Behavior

When invoked:

1. **Scan all documentation:**
   - `docs/**/*.md`
   - `README.md`

2. **Validate links:**

   ### Internal
   - Verify referenced files exist (e.g. `kotlin/androidSampleApp`, `flutter/sample/`)
   - Verify referenced paths in docs resolve correctly

   ### External
   - Verify URLs are reachable (e.g. GitHub, Maven Central, pub.dev)

3. **Verify configuration examples match actual project:**

   ### Kotlin / Gradle
   - Version catalog snippets match `kotlin/gradle/libs.versions.toml`
   - `build.gradle.kts` examples match `kotlin/jujubasvg/build.gradle.kts`
   - Plugin coordinates match actual publishing config (`io.github.codandotv:jujubaSVG`)

   ### Flutter
   - `pubspec.yaml` snippets match `flutter/jujuba_svg/pubspec.yaml`
   - Package installation instructions match current version
   - API usage examples match actual public API

4. **Verify documented APIs exist:**
   - Kotlin public APIs (e.g. `JujubaSVG`, `JujubaCommander`, `Command`)
   - Flutter public APIs (e.g. `JujubaSVGWidget`, `JujubaCommander`)

5. **Verify setup instructions are accurate** (cross-check against `README.md`, build files, `pubspec.yaml`).

6. **Verify release instructions are still valid** (check `mkdocs.yml`, GitHub Actions workflows in `.github/`).

7. **Report results:**

   ### Errors
   Documentation is incorrect or broken (dead links, wrong API names).

   ### Warnings
   Documentation is outdated or incomplete (missing sections, stale version references).

   ### Suggestions
   Recommended improvements (clarify ambiguous steps, add missing examples).
