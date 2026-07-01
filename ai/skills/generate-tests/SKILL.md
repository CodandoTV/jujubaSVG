---
name: generate-tests
description: Generate tests following the project's existing conventions
---

# generate-tests

## Goal

Generate tests that follow the project's existing conventions.

## Behavior

When invoked:

1. **Detect testing framework:**

   ### Kotlin / KMP
   - Check for `kotlin("test")` in `kotlin/jujubasvg/build.gradle.kts`
   - Check for additional libraries: `kotlinx-coroutines-test`, `turbine`
   - Tests live in `commonTest/` (shared across platforms)

   ### Flutter
   - Check for `flutter_test` in `flutter/jujuba_svg/pubspec.yaml`

2. **Analyze existing tests:**
   - Kotlin: `kotlin/jujubasvg/src/commonTest/` — examine file names, class structure, assertion style
   - Flutter: `flutter/jujuba_svg/test/` — examine file names, widget test patterns, assertion style

3. **Follow current naming conventions:**
   - Kotlin: `<Subject>Test.kt` with descriptive test function names
   - Flutter: `<subject>_test.dart` with `test()` / `group()` / `expect()`

4. **Generate appropriate test types:**

   ### Kotlin / KMP
   - Unit tests for `JujubaCommander`, `Command` execution, SVG node manipulation
   - Place tests in `commonTest/` unless they require Android-specific APIs
   - Use `kotlin.test` assertions + `kotlinx.coroutines.test` for coroutine testing
   - Use `Turbine` for `Flow`/`SharedFlow` testing

   ### Flutter
   - Unit tests for `JujubaCommander` logic
   - Widget tests for `JujubaSVGWidget` rendering
   - Use `flutter_test` with `WidgetTester`, `pumpWidget`, `find`, `expect`

5. **Ensure generated tests use the project's assertion style and test architecture:**
   - Kotlin: functional/behavioral style matching existing tests
   - Flutter: `testWidgets`, group-based organization, `mockito` if used
