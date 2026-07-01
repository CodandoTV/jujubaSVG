# Kotlin Multiplatform / Compose — Platform Instructions

## Project Structure

```
kotlin/
├── build-logic/             # Convention plugins (included build)
│   └── src/main/kotlin/
│       ├── config/Config.kt
│       ├── ext/ProjectExt.kt
│       └── plugins/
│           ├── android-app-plugin.gradle.kts
│           └── kmp-library-plugin.gradle.kts
├── jujubasvg/               # 📦 Public KMP library module
│   ├── src/
│   │   ├── commonMain/   # Shared cross-platform code (SVG rendering, commander, models)
│   │   ├── androidMain/  # Android-specific implementations (resource-based composable)
│   │   └── commonTest/   # Shared cross-platform tests
│   ├── build.gradle.kts
│   ├── version.properties
│   └── gradle.properties
├── androidSampleApp/        # Consumer Android sample app
├── gradle/libs.versions.toml  # Version catalog
├── gradle/wrapper/
├── build.gradle.kts
├── gradle.properties
└── settings.gradle.kts
```

## Key Dependencies (version catalog)

| Alias | Artifact | Purpose |
|-------|----------|---------|
| `kotlin` | Kotlin 2.4.0 | Language |
| `gradle` | AGP 9.1.0 | Android build |
| `jetbrains_compose` | Compose Multiplatform 1.11.1 | UI toolkit |
| `compose-webview-multiplatform` | KMP WebView | SVG rendering via WebView |
| `components-resources` | Compose Resources | Asset loading (`Res.readBytes`) |
| `kotlinx-serialization-json` | kotlinx.serialization | JS bridge message deserialization |
| `kermit` | Kermit 2.0.4 | Multiplatform logging |
| `kotlinx-coroutines-test` | Test utilities | Coroutine testing |
| `turbine` | Turbine | Flow/Channel testing |

## Source Set Conventions

| Source Set | Purpose | Depends On |
|-----------|---------|------------|
| `commonMain` | Shared business logic, models, commander, SVG rendering | Compose Multiplatform, KMP WebView, kotlinx.serialization, Kermit |
| `androidMain` | Android-specific composable (raw resource loading, `InputStream.fileTextContent()`) | `commonMain` |
| `commonTest` | Shared unit tests | `commonMain`, `kotlin.test`, `kotlinx-coroutines-test` |

### Platform-specific code
- Use `expect`/`actual` declarations for platform-specific APIs (e.g. `InputStream.fileTextContent()`)
- Android-specific resources are loaded via `Res.readBytes()` from `composeResources/files/`
- Compose Resources live in `commonMain/composeResources/` (HTML template, JS bridge script)

## Code Conventions

### Naming
- **Classes**: PascalCase
- **Functions/properties**: camelCase
- **Test files**: `<Subject>Test.kt`
- **Test functions**: descriptive sentences (e.g. `fun updateBackgroundColor_emitsCorrectJS()`)

### Compose Multiplatform
- State hoisting via `JujubaCommander` (interface consumed as `remember`/`rememberSaveable`)
- SVG passed as `svgText: String` parameter or loaded from resources via `@RawRes`
- Elements parsed and rendered in a composable tree using `WebView` + `WebViewJsBridge`
- JS bridge replaces `addJavascriptInterface` — messages serialized via `kotlinx.serialization`

### Public API
- All public types marked explicitly (`explicitApi()` enforced)
- Internal packages must not appear in public API surface
- Document all public API with KDoc
- Common API lives in `commonMain`; Android-specific overloads in `androidMain`

### Testing
- Testing framework: `kotlin("test")` (JUnit 5 style, common module)
- Coroutines: `kotlinx-coroutines-test` with `runTest`
- Flow testing: Turbine library
- Assertions: `kotlin.test` (`assertEquals`, `assertTrue`, etc.)
- Tests live in `commonTest/` (shared across platforms)

## Common Tasks

| Task | Command | Work Directory |
|------|---------|----------------|
| Run unit tests | `./gradlew jujubasvg:allTests` | `kotlin/` |
| Run Android-specific tests | `./gradlew jujubasvg:testDebugUnitTest` | `kotlin/` |
| Build all | `./gradlew assemble` | `kotlin/` |
| Clean | `./gradlew clean` | `kotlin/` |
