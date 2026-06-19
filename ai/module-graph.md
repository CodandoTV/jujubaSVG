# Module Dependency Graph — JujubaSVG

## Android (Gradle)

```
:build-logic (convention plugins)
  ├── provides android-app-plugin   → :sampleApp
  └── provides android-library-plugin → :jujubasvg

:jujubasvg (Kotlin library)
  └── no internal Android dependencies (pure Compose + coroutines)

:sampleApp (Android app)
  └── depends on :jujubasvg (public API consumption)
```

### Rules
- `:jujubasvg` **must never** depend on `:sampleApp`
- `:build-logic` is a composed build (`includeBuild("build-logic")` in settings)
- Convention plugins are applied; their classes must not leak into consumer classpaths
- `explicitApi()` is enabled on all Kotlin modules — types must be explicitly marked `public`

---

## Flutter (Dart)

```
jujuba_svg/ (Flutter package)
  ├── lib/core/       ← business logic, commander
  ├── lib/model/      ← data models (NodeInfo, NodeCoordinate)
  └── lib/util/       ← utilities (asset_helper)

sample/ (Flutter app)
  └── depends on jujuba_svg/ (local path dependency in pubspec.yaml)
```

### Rules
- `lib/core/` must **not** depend on widgets or `flutter/material.dart`
- `lib/model/` must stay free of UI references
- `jujuba_svg/` must **never** depend on `sample/`
- Sample app uses `jujuba_svg` via path dependency at the monorepo root

---

## Cross-Platform Boundaries

| Concern | Android | Flutter |
|---------|---------|---------|
| SVG parsing & rendering | Compose-based (custom) | WebView-based (`webview_flutter`) |
| Commander pattern | `JujubaCommander` class | `JujubaCommander` class |
| Command types | `Command.RemoveNode`, etc. | Mirroring Android API |
| Asset handling | Android resources | `pubspec.yaml` assets |
| Testing | JUnit 5 + kotlinx-coroutines-test + Turbine | flutter_test + mockito |
