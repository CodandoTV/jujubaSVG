# AGENTS.md — JujubaSVG

## Project Overview

**JujubaSVG** is a dual-platform library for handling SVG files in Kotlin Multiplatform (Compose Multiplatform) and Flutter applications. It enables granular manipulation of SVG elements — access any element by ID to modify properties like background color, stroke, and other attributes. This file is the **single source of truth** for OpenCode sessions in this repository.

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
├── .opencode/skills/        # OpenCode skills — auto-discovered from this path
├── ai/instructions/         # Platform conventions: kotlin.md, flutter.md
├── kotlin/
│   ├── build-logic/         # Convention plugins (included build)
│   ├── jujubasvg/           # 📦 KMP library — public API
│   │   └── src/{commonMain,androidMain,commonTest}/
│   ├── sampleApp/           # KMP sample module (builds the `sampleApp` iOS framework)
│   ├── androidSampleApp/    # Android sample app
│   ├── iosSampleApp/        # Xcode project consuming sampleApp's iOS framework
│   └── gradle/              # Wrapper + libs.versions.toml
├── flutter/
│   ├── jujuba_svg/          # 📦 Flutter package — public API (lib/{core,model,util}, js/, example/)
│   └── sample/              # Flutter sample app
├── docs/                    # Zensical documentation source (nav in mkdocs.yml)
├── scripts/detektcheck.sh
└── opencode.json            # OpenCode config
```

---

## Module Dependency Graph

### Kotlin (Gradle / KMP)

```
:build-logic (convention plugins)
  ├── kmp-library-plugin   → :jujubasvg, :sampleApp
  └── android-app-plugin   → :androidSampleApp

:jujubasvg (KMP library)
  ├── commonMain/    shared code (SVG rendering, commander, models, JS bridge)
  ├── androidMain/   Android-specific composable overloads (loads SVG from resources)
  └── commonTest/    shared cross-platform tests

:sampleApp (KMP sample, applies kmp-library-plugin) → :jujubasvg
:androidSampleApp (Android app) → :sampleApp
:iosSampleApp (Xcode project) → :sampleApp iOS framework
```

### Flutter (Dart)

```
flutter/sample/ → flutter/jujuba_svg/ (local path dependency in pubspec.yaml)

jujuba_svg/lib/
  ├── core/   business logic, commander
  ├── model/  data models (NodeInfo, NodeCoordinate)
  └── util/   utilities (asset_helper)
```

### Cross-Platform Boundaries

| Concern | Kotlin (KMP) | Flutter |
|---------|-------------|---------|
| SVG rendering | Compose Multiplatform WebView + JS bridge | WebView (`webview_flutter`) |
| Commander | `JujubaCommander` class | `JujubaCommander` class |
| Command types | `Command.UpdateBackgroundColor`, etc. | Mirroring Kotlin API |
| Asset handling | Compose Resources (`Res.readBytes`) | `pubspec.yaml` assets |
| Logging | Kermit (multiplatform) | Dart `print` |
| JS bridge | `WebViewJsBridge` + kotlinx.serialization | `WebViewController.evaluateJavascript` |
| Testing | `kotlin.test` + `kotlinx-coroutines-test` | `flutter_test` |

---

## Platform Context

Load the appropriate instruction file based on what you are modifying:

| If you are working on...       | Read this first                                      |
|-------------------------------|------------------------------------------------------|
| **Kotlin Multiplatform**      | `ai/instructions/kotlin.md`                          |
| **Flutter / Dart**            | `ai/instructions/flutter.md`                         |
| **Both / cross-platform**     | Read both `ai/instructions/kotlin.md` and `ai/instructions/flutter.md` |
| **Documentation**             | `docs/` — Zensical source                            |

---

## Available Skills

Before starting any task, list `.opencode/skills/`, identify which covers the task, then read `.opencode/skills/<name>/SKILL.md` in full before proceeding.

| Skill | When to use |
|-------|-------------|
| `documentation-review` | Validate docs against implementation |
| `generate-tests` | Write new tests following conventions |
| `minimum-requirements` | Determine/update consumption requirements |
| `trigger-release` | Trigger a Kotlin or Flutter release with version bump, changelog, and tag |
| `validate-architecture` | Verify module dependency rules |
| `open-pr` | Compare branch vs main and open a pull request via gh CLI |

---

## How to Implement Tasks

### Build Validation

| Platform | Command | Work Directory |
|----------|---------|----------------|
| Kotlin (Android host tests) | `./gradlew jujubasvg:testAndroidHostTest` | `kotlin/` |
| Kotlin (build) | `./gradlew assemble` | `kotlin/` |
| Flutter (deps) | `flutter pub get` — run in **both** `jujuba_svg/` and `sample/` | `flutter/` |
| Flutter (test) | `flutter test` | `flutter/jujuba_svg/` |
| Flutter (analyze) | `dart analyze` | `flutter/` |
| Kotlin lint (detekt) | `detekt --input . -c config/detekt/detekt.yml -ex "**/build/**"` (see `scripts/detektcheck.sh`) | `kotlin/` |
| Documentation | `zensical build` (CI pins `zensical==0.0.46`) | root |

### When to Mark Done

1. Write/fix code
2. Run the appropriate build/test command (see table above)
3. If tests fail, fix and re-run
4. Add or update tests if the change modifies public API behavior
5. Commit with a conventional commit message (`feat:`, `fix:`, `chore:`, `docs:`, `refactor:`)

---

## Critical Architectural Rules

1. **No circular dependencies** — `:jujubasvg` must never depend on the sample modules. On Flutter, `jujuba_svg/` must never depend on `sample/`.
2. **`explicitApi()` enforcement** — Kotlin modules enable `explicitApi()`. All public API must be explicitly declared; never mark internal types as `public`.
3. **Layer separation (Flutter)** — `lib/core/` must not depend on widgets or `flutter/material.dart`. `lib/model/` must stay free of UI references.
4. **Internal packages must not leak** — Android internal packages (anything under `internal/`) must not appear in the public API surface.
5. **Convention plugins** — `build-logic/` provides shared config. Library modules apply `kmp-library-plugin`; app modules apply `android-app-plugin`. Never duplicate config across module-level `build.gradle.kts`. `:build-logic` is an included build (`includeBuild("build-logic")`) and its classes must not leak into consumer classpaths.
6. **Source sets** — `commonMain` must not use Android-specific APIs (use `expect`/`actual`); `androidMain` extends `commonMain` and may. Compose Resources live in `commonMain/composeResources/`.

---

## Cross-Platform Invariants

- **JS bridge parity** — `flutter/jujuba_svg/js/base_js.js` and `kotlin/jujubasvg/src/commonMain/composeResources/files/base_js.js` must stay byte-identical. `js-validator.yml` runs `diff` on them and fails the PR if they diverge; edit both copies together.
- **Versions are per-platform and independent** — Kotlin lives in `kotlin/jujubasvg/version.properties` (`VERSION=x.y.z`), Flutter in `flutter/jujuba_svg/pubspec.yaml` (`version: x.y.z`). They have separate changelogs and release tags (`jujubasvg-kotlin-*` / `jujubasvg-flutter-*`) and are not required to match.
- **Flutter public API docs** — `flutter/jujuba_svg/analysis_options.yaml` enables the `public_member_api_docs` lint, so every public member needs a `///` doc comment or `dart analyze` fails.

---

## CI / Automation Overview

| Workflow | Trigger | What it does |
|----------|---------|--------------|
| `android-build.yml` | PR to `main` touching `kotlin/` | `./gradlew jujubasvg:testAndroidHostTest` |
| `flutter-build.yml` | PR to `main` touching `flutter/` | `flutter pub get` + `flutter test` + `dart analyze` |
| `documentation.yml` | PR to `main` touching `docs/` or `mkdocs.yml` | Builds & deploys docs with Zensical to GitHub Pages |
| `js-validator.yml` | all PRs | Diffs the two `base_js.js` copies |
| `megalinter.yml` | all PRs | MegaLinter (detekt, prettier, markdown table formatter, htmlhint) |
| `publish.yml` | Tag `jujubasvg-kotlin-*` | `./gradlew jujubasvg:publish` → Maven Central |
| `publish-flutter.yml` | Tag `jujubasvg-flutter-*` | Publishes to pub.dev |

---

## PR Review Checklist

Before submitting a pull request, verify:

- [ ] All new code has corresponding tests (unit tests for logic, widget tests for UI)
- [ ] `./gradlew jujubasvg:testAndroidHostTest` passes (if Kotlin changed)
- [ ] `flutter test` passes (if Flutter changed)
- [ ] `dart analyze` passes (if Flutter changed)
- [ ] `base_js.js` copies are identical (if the JS bridge changed)
- [ ] Public API is documented with KDoc/Doc comments
- [ ] No internal types leaked in public API
- [ ] Version files updated if releasing
- [ ] `CHANGELOG.md` entries added for user-facing changes
