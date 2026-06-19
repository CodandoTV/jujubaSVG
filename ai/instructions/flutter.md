# Flutter / Dart — Platform Instructions

## Project Structure

```
flutter/
├── jujuba_svg/                 # 📦 Public package
│   ├── lib/
│   │   ├── core/commander/     # JujubaCommander business logic
│   │   ├── core/constants.dart
│   │   ├── core/jujuba_widget.dart  # JujubaSVGWidget composable
│   │   ├── model/              # NodeInfo, NodeCoordinate
│   │   └── util/               # AssetHelper
│   ├── test/
│   ├── js/base_js.js           # JavaScript bridge for WebView
│   ├── pubspec.yaml
│   └── analysis_options.yaml
├── sample/                     # Consumer sample app
│   ├── lib/
│   ├── android/
│   ├── ios/
│   └── pubspec.yaml
├── .gitignore
```

## Key Dependencies

| Package | Version | Purpose |
|---------|---------|---------|
| `flutter` | SDK | UI framework |
| `webview_flutter` | ^4.13.0 | SVG rendering via WebView |
| `flutter_test` | SDK | Testing |
| `flutter_lints` | ^6.0.0 | Linting rules |

## Code Conventions

### Naming
- **Classes**: PascalCase
- **Functions/properties**: camelCase
- **Files**: `snake_case.dart`
- **Test files**: `<subject>_test.dart`

### Architecture
- `lib/core/` — business logic (no widget/UI dependencies)
- `lib/model/` — data models (plain Dart classes, no UI imports)
- `lib/util/` — utilities
- `JujubaSVGWidget` exposes SVG rendering via `webview_flutter`
- `JujubaCommander` handles command execution (mirrors Android API)

### Public API
- Document all public API with Doc comments (`///`)
- Export public API from the package entry point
- Internal classes should be library-private (prefix `_`)

### Testing
- Framework: `flutter_test` with `testWidgets`
- Widget tests: `pumpWidget`, `find`, `expect`
- Unit tests: `test()` with `expect()`
- No mockito currently used but available for future use

## Common Tasks

| Task | Command |
|------|---------|
| Install deps | `flutter pub get` |
| Run tests | `flutter test` |
| Analyze | `dart analyze` |
| Format | `dart format .` |
