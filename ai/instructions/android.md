# Android / Kotlin — Platform Instructions

## Project Structure

```
android/
├── build-logic/             # Convention plugins (included build)
│   └── src/main/kotlin/
│       ├── config/Config.kt
│       ├── ext/ProjectExt.kt
│       └── plugins/
│           ├── android-app-plugin.gradle.kts
│           └── android-library-plugin.gradle.kts
├── jujubasvg/               # 📦 Public library module
│   └── src/
│       ├── main/kotlin/com/github/gabrielbmoro/jujubasvg/
│       └── test/kotlin/com/github/gabrielbmoro/jujubasvg/
├── sampleApp/               # Consumer sample app
├── config/detekt/           # Detekt static analysis config
├── gradle/libs.versions.toml  # Version catalog
├── gradle/wrapper/
├── build.gradle.kts
├── gradle.properties
└── settings.gradle.kts
```

## Key Dependencies (version catalog)

| Alias | Artifact | Purpose |
|-------|----------|---------|
| `kotlin` | Kotlin 2.1.0 | Language |
| `gradle` | AGP 8.13.1 | Android build |
| `compose.bom` | Compose BOM | UI toolkit |
| `kotlinx-coroutines-test` | Test utilities | Coroutine testing |
| `turbine` | Turbine | Flow/Channel testing |

## Code Conventions

### Naming
- **Classes**: PascalCase
- **Functions/properties**: camelCase
- **Test files**: `<Subject>Test.kt`
- **Test functions**: descriptive sentences (e.g. `fun removeNode_removesElementFromSvg()`)

### Compose
- State hoisting via `JujubaCommander` (interface consumed as `remember`/`rememberSaveable`)
- SVG passed as `svgText: String` parameter
- Elements parsed and rendered in a composable tree

### Public API
- All public types marked explicitly (`explicitApi()` enforced)
- Internal packages must not appear in public API surface
- Document all public API with KDoc

### Testing
- Testing framework: `kotlin("test")` (JUnit 5 style)
- Coroutines: `kotlinx-coroutines-test` with `runTest`
- Flow testing: Turbine library
- Assertions: `kotlin.test` (`assertEquals`, `assertTrue`, etc.)

## Common Tasks

| Task | Command |
|------|---------|
| Run unit tests | `./gradlew jujubasvg:testDebugUnitTest` |
| Build all | `./gradlew assemble` |
| Run detekt | `./gradlew detekt` |
| Clean | `./gradlew clean` |
