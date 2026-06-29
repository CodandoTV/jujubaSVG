# Module Dependency Graph — JujubaSVG

## Kotlin (Gradle / KMP)

```
:build-logic (convention plugins)
  ├── provides kmp-library-plugin   → :jujubasvg
  └── provides android-app-plugin   → :androidSampleApp

:jujubasvg (KMP library)
  ├── src/commonMain/    ← shared cross-platform code
  │     (SVG rendering, commander, models, JS bridge)
  ├── src/androidMain/   ← Android-specific composable overloads
  │     (loads SVG from Android resources)
  └── src/commonTest/    ← shared cross-platform tests

:androidSampleApp (Android app)
  └── depends on :jujubasvg (public API consumption)
```

### Rules
- `:jujubasvg` **must never** depend on `:androidSampleApp`
- `:build-logic` is an included build (`includeBuild("build-logic")` in settings)
- Convention plugins are applied; their classes must not leak into consumer classpaths
- `explicitApi()` is enabled on all Kotlin modules — types must be explicitly marked `public`
- `commonMain` code must not use Android-specific APIs (use `expect`/`actual`)
- `androidMain` code extends `commonMain`; may use Android-specific APIs
- Compose Multiplatform Resources stored in `commonMain/composeResources/`

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

| Concern | Kotlin (KMP) | Flutter |
|---------|-------------|---------|
| SVG rendering | Compose Multiplatform WebView + JS bridge | WebView-based (`webview_flutter`) |
| Commander pattern | `JujubaCommander` class (SharedFlow) | `JujubaCommander` class |
| Command types | `Command.UpdateBackgroundColor`, etc. | Mirroring Kotlin API |
| Asset handling | Compose Resources (`Res.readBytes`) | `pubspec.yaml` assets |
| Logging | Kermit (multiplatform) | Dart `print` |
| JS bridge | `WebViewJsBridge` + kotlinx.serialization | `WebViewController.evaluateJavascript` |
| Testing | `kotlin.test` + `kotlinx-coroutines-test` | `flutter_test` |
