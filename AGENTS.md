# AGENTS.md — JujubaSVG

## Project Overview

**JujubaSVG** is a dual-platform library for handling SVG files in Android (Kotlin/Jetpack Compose) and Flutter applications. It enables granular manipulation of SVG elements — access any element by ID to modify properties like background color, stroke, and other attributes.

### Library Types

- Kotlin Library (Android)
- Flutter Package

### Primary Language and Version

- Kotlin 2.1.0
- Dart ^3.5.0

### Runtime / Platform

- JVM (Android)
- Flutter (Android, iOS)

### Supported Environments

- Android API 22+
- Flutter via Dart SDK ^3.5.0

---

## Build System

### Kotlin / Gradle

| Property          | Value                        |
|-------------------|------------------------------|
| Gradle version    | 9.0-milestone-1 (wrapper)    |
| Kotlin version    | 2.1.0                        |
| AGP version       | 8.13.1                       |
| Version Catalog   | `android/gradle/libs.versions.toml` |
| Convention plugins| `android/build-logic/`       |
| Publishing plugin | `com.vanniktech.maven.publish` (0.28.0) |

### Dart / Flutter

| Property          | Value                        |
|-------------------|------------------------------|
| Dart SDK          | ^3.5.0                       |
| Flutter           | (latest stable)              |
| Dependencies      | See `flutter/jujuba_svg/pubspec.yaml` |

---

## Project Structure

```
jujubaSVG/
├── android/
│   ├── build-logic/           # Convention plugins
│   ├── jujubasvg/             # Kotlin/Android library (public API)
│   ├── sampleApp/             # Android sample application
│   ├── config/                # Static analysis config
│   ├── gradle/                # Wrapper + version catalog
│   ├── build.gradle.kts
│   └── settings.gradle.kts
├── flutter/
│   ├── jujuba_svg/            # Flutter/Dart package (public API)
│   │   ├── lib/               # Source
│   │   └── test/              # Tests
│   └── sample/                # Flutter sample application
├── docs/                      # MkDocs documentation source
├── img/                       # Images / assets
├── scripts/                   # Helper scripts
├── mkdocs.yml                 # MkDocs configuration
└── README.md
```

---

## Version Management

| Location                          | Format     | Current Version |
|-----------------------------------|------------|-----------------|
| `android/jujubasvg/version.properties` | `VERSION=x.y.z` | 1.3.0           |
| `flutter/jujuba_svg/pubspec.yaml` | `version: x.y.z` | 1.1.1           |

---

## Documentation

- **Generator:** MkDocs with Material theme
- **Configuration:** `mkdocs.yml`
- **Source:** `docs/`
- **Published at:** https://codandotv.github.io/jujubaSVG/

---

## Distribution

### Kotlin Library (Maven Central)

| Field        | Value                              |
|--------------|------------------------------------|
| Group ID     | `io.github.codandotv`              |
| Artifact ID  | `jujubaSVG`                        |
| Sonatype     | Central Portal (`CENTRAL_PORTAL`)  |

### Flutter Package (pub.dev)

| Field        | Value                              |
|--------------|------------------------------------|
| Package name | `jujuba_svg`                       |
| URL          | https://pub.dev/packages/jujuba_svg |

---

## Git Workflow

- **Default branch:** `main`
- **Versioning:** Semantic Versioning (SemVer)
- **Tags:** `v<major>.<minor>.<patch>` (e.g. `v1.2.0`)
- **Release process:** Tag on `main` → CI publishes to Maven Central and/or pub.dev
