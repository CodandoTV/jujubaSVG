# AGENTS.md — JujubaSVG

## Project Overview

**JujubaSVG** is a dual-platform library for handling SVG files in Kotlin Multiplatform (Compose Multiplatform) and Flutter applications. It enables granular manipulation of SVG elements — access any element by ID to modify properties like background color, stroke, and other attributes. This file is the **single source of truth** for all AI coding assistants operating in this repository.

| Property | Value |
|----------|-------|
| Kotlin Library | `kotlin/jujubasvg/` (Compose Multiplatform) |
| Flutter Package | `flutter/jujuba_svg/` |
| Kotlin version | 2.4.0 |
| Dart SDK | ^3.5.0 |
| Min Android API | 23 |
| License | MIT (see `license` file) |

---

## Folder / Module Structure

```
jujubaSVG/
├── ai/                          # Centralized AI context (module graph, instructions, skills)
├── kotlin/
│   ├── build-logic/             # Convention plugins (app + KMP library)
│   │   └── src/main/kotlin/
│   │       ├── config/Config.kt
│   │       ├── ext/ProjectExt.kt
│   │       └── plugins/
│   │           ├── android-app-plugin.gradle.kts
│   │           └── kmp-library-plugin.gradle.kts
│   ├── jujubasvg/               # 📦 KMP library — public API (Compose Multiplatform)
│   │   └── src/{commonMain,androidMain,commonTest}/
│   ├── androidSampleApp/        # Android sample application
│   ├── gradle/                  # Wrapper + libs.versions.toml
│   ├── build.gradle.kts
│   └── settings.gradle.kts
├── flutter/
│   ├── jujuba_svg/              # 📦 Flutter/Dart package — public API
│   │   ├── lib/{core,model,util}/
│   │   └── test/
│   └── sample/                  # Flutter sample application
├── docs/                        # MkDocs documentation source
├── scripts/
├── mkdocs.yml
├── README.md
└── AGENTS.md                    # ← you are here
```

---

## Platform Context

Load the appropriate instruction file based on what you are modifying:

| If you are working on...       | Read this first                                      |
|-------------------------------|------------------------------------------------------|
| **Kotlin Multiplatform**      | `ai/instructions/kotlin.md`                          |
| **Flutter / Dart**            | `ai/instructions/flutter.md`                         |
| **Both / cross-platform**     | Read both `ai/instructions/kotlin.md` and `ai/instructions/flutter.md` |
| **Documentation**             | `docs/` — MkDocs source                              |

---

## Available Skills

Before starting any task, list directories in `ai/skills/`, identify which covers the task, then read `ai/skills/<name>/SKILL.md` in full before proceeding.

| Skill | When to use |
|-------|-------------|
| `documentation-review` | Validate docs against implementation |
| `generate-tests` | Write new tests following conventions |
| `minimum-requirements` | Determine/update consumption requirements |
| `release-notes` | Prepare a release |
| `validate-architecture` | Verify module dependency rules |
| `open-pr` | Compare branch vs main and open a pull request via gh CLI |

---

## How to Implement Tasks

### Build Validation

| Platform | Command | Work Directory |
|----------|---------|----------------|
| Kotlin (Android host tests) | `./gradlew jujubasvg:testAndroidHostTest` | `kotlin/` |
| Kotlin (build) | `./gradlew assemble` | `kotlin/` |
| Flutter (deps) | `flutter pub get` | `flutter/jujuba_svg/` |
| Flutter (test) | `flutter test` | `flutter/jujuba_svg/` |
| Flutter (analyze) | `dart analyze` | `flutter/` |
| Documentation | `mkdocs serve` | root |

### When to Mark Done

1. Write/fix code
2. Run the appropriate build/test command (see table above)
3. If tests fail, fix and re-run
4. Add or update tests if the change modifies public API behavior
5. Commit with a conventional commit message (`feat:`, `fix:`, `chore:`, `docs:`, `refactor:`)

---

## Critical Architectural Rules

1. **No circular dependencies** — `:jujubasvg` must never depend on `:sampleApp`. On Flutter, `jujuba_svg/` must never depend on `sample/`.
2. **`explicitApi()` enforcement** — Kotlin modules enable `explicitApi()`. All public API must be explicitly declared; never mark internal types as `public`.
3. **Layer separation (Flutter)** — `lib/core/` (business logic) must not depend on widgets. `lib/model/` must stay free of UI references.
4. **Internal packages must not leak** — Android internal packages (e.g. anything under `internal/`) must not appear in the public API surface.
5. **Version consistency** — Kotlin version lives in `kotlin/jujubasvg/version.properties` (`VERSION=x.y.z`). Flutter version lives in `flutter/jujuba_svg/pubspec.yaml` (`version: x.y.z`). Both must stay in sync for a cross-platform release.
6. **Convention plugins** — `build-logic/` provides shared config. Library modules apply `kmp-library-plugin`; app modules apply `android-app-plugin`. Never duplicate config across module-level `build.gradle.kts`.

---

## CI / Automation Overview

| Workflow | Trigger | What it does |
|----------|---------|--------------|
| `android-build.yml` | PR to `main` touching `kotlin/` | Runs `jujubasvg:testAndroidHostTest` |
| `flutter-build.yml` | PR to `main` touching `flutter/` | Runs `flutter test` + `dart analyze` |
| `documentation.yml` | PR to `main` touching `docs/` or `mkdocs.yml` | Builds & deploys MkDocs to GitHub Pages |
| `megalinter.yml` | (all PRs) | MegaLinter static analysis |
| `js-validator.yml` | (as configured) | JavaScript validation |
| `publish-android.yml` | `workflow_dispatch` | Publishes to Maven Central |
| `publish-flutter.yml` | Tag `jujubasvg-flutter-*` | Publishes to pub.dev |

---

## PR Review Checklist

Before submitting a pull request, verify:

- [ ] All new code has corresponding tests (unit tests for logic, widget tests for UI)
- [ ] `./gradlew jujubasvg:testAndroidHostTest` passes (if Kotlin changed)
- [ ] `flutter test` passes (if Flutter changed)
- [ ] `dart analyze` passes (if Flutter changed)
- [ ] Public API is documented with KDoc/Doc comments
- [ ] No internal types leaked in public API
- [ ] Version files updated if releasing
- [ ] `CHANGELOG.md` entries added for user-facing changes
