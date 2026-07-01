# Changelog

All notable changes to the JujubaSVG Kotlin/Android library.

---

## [Unreleased]

### Added
- `CustomCommand` — execute arbitrary JavaScript inside the SVG WebView ([#81](https://github.com/CodandoTV/jujubaSVG/pull/81))
- Kotlin Multiplatform restructuring with `commonMain`, `androidMain`, and `commonTest` source sets
- Multiplatform WebView JS bridge (`WebViewJsBridge`) replacing `addJavascriptInterface`
- Compose Multiplatform Resources for asset loading (`Res.readBytes`)
- Kermit multiplatform logging replacing Android-specific logger
- Convention plugins: `kmp-library-plugin`, `android-app-plugin`
- Gradle version catalog for dependency management

### Changed
- Gradle upgraded to 8.13.2
- Kotlin upgraded to 2.1.0
- SDK versions updated (compile/target SDK 36, min SDK 23)
- `base_js.js` asset now synced with the Flutter package

---

## [1.2.0] - 2025-10-02

### Added
- MkDocs documentation site ([#50](https://github.com/CodandoTV/jujubaSVG/pull/50))
- CODE_OF_CONDUCT.md ([#53](https://github.com/CodandoTV/jujubaSVG/pull/53))
- MegaLinter integration for YAML, Markdown, and JavaScript linting ([#53](https://github.com/CodandoTV/jujubaSVG/pull/53))

### Changed
- Kotlin version updated
- Switched to JetBrains Compose compiler plugin
- Optimized publish CI pipeline ([#49](https://github.com/CodandoTV/jujubaSVG/pull/49))

### Removed
- Detekt removed from the build system (linting consolidated in MegaLinter) ([#57](https://github.com/CodandoTV/jujubaSVG/pull/57))

---

## [1.1.0] - 2025-07-07

### Changed
- **Breaking:** Package renamed from `com.gabrielbmoro.jujubasvg` to `com.github.codandotv.jujubasvg` ([#46](https://github.com/CodandoTV/jujubaSVG/pull/46))
- Updated sample app icon and branding ([#45](https://github.com/CodandoTV/jujubaSVG/pull/45))

### Added
- Detekt static analysis integrated ([#42](https://github.com/CodandoTV/jujubaSVG/pull/42))

### Fixed
- Detekt issues resolved across the codebase ([#42](https://github.com/CodandoTV/jujubaSVG/pull/42))

---

## [1.0.3] - 2025-06-25

### Changed
- **Breaking:** All `colorInHex: String` parameters replaced with `color: Color` (Jetpack Compose `Color`) ([#32](https://github.com/CodandoTV/jujubaSVG/pull/32))
- Background and stroke color parameter names updated for consistency ([#31](https://github.com/CodandoTV/jujubaSVG/pull/31))

### Added
- Alpha channel support for all color commands via `Color.toHex()` extension
- Proper `SharedFlow` exposure pattern ([#30](https://github.com/CodandoTV/jujubaSVG/pull/30))

---

## [1.0.2] - 2025-06-22

### Added
- Multi-command chunked execution via `execute(vararg command: Command)` ([#29](https://github.com/CodandoTV/jujubaSVG/pull/29))
- Debug logging for JavaScript evaluation results ([#29](https://github.com/CodandoTV/jujubaSVG/pull/29))

### Changed
- Migrated from `StateFlow` to `SharedFlow` for command streaming ([#29](https://github.com/CodandoTV/jujubaSVG/pull/29))
- Made command collection lifecycle-aware ([#29](https://github.com/CodandoTV/jujubaSVG/pull/29))

---

## [1.0.1] - 2025-06-20

### Fixed
- Circle SVG element rendering issue ([#28](https://github.com/CodandoTV/jujubaSVG/pull/28))

---

## [1.0.0] - 2025-06-18

### Added
- `JujubaSVG` composable — renders SVG files from raw resources or plain text
- `JujubaCommander` — queues and dispatches JavaScript commands into the SVG WebView
- `rememberJujubaCommander()` — creates and remembers a commander instance
- **Built-in commands:**
  - `UpdateBackgroundColor` — change an element's fill color
  - `UpdateStrokeColor` — change an element's stroke color
  - `UpdateStrokeWidth` — change an element's stroke thickness
  - `RemoveNode` — remove an SVG element
  - `UpdateRootBackgroundColor` — change the root background color
  - `AddRoundedImage` — place a rounded image at an element's parent level ([#26](https://github.com/CodandoTV/jujubaSVG/pull/26))
- Element tap detection via `onElementClick: (NodeInfo) -> Unit` callback
- `NodeInfo` and `NodeCoordinate` model classes for click event data
- Modular HTML/JS template (`base_js.js`, `jujuba.html`)
- WebView ready state handling to prevent command race conditions
