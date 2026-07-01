---
name: release-notes
description: Generate release notes and prepare the next release
---

# release-notes

## Goal

Generate release notes and prepare the next release.

## Behavior

When invoked:

1. **Detect current version** from:
   - `kotlin/jujubasvg/version.properties` (property `VERSION`)
   - `flutter/jujuba_svg/pubspec.yaml` (field `version`)

2. **Get latest Git tag:**
   ```bash
   git tag --sort=-v:refname | head -1
   ```

3. **Analyze unreleased changes:**
   ```bash
   git log <latest-tag>..HEAD --oneline
   ```

4. **Categorize commits:**

   | Bump    | Patterns                            |
   |---------|-------------------------------------|
   | Major   | `breaking`, `BREAKING CHANGE`       |
   | Minor   | `feat`                              |
   | Patch   | `fix`, `docs`, `chore`, `refactor`, `test` |

5. **Determine next semantic version** based on categorized commits.

6. **Update version file** (increment in `version.properties` or `pubspec.yaml`).

7. **Create or update `CHANGELOG.md`:**

   ```md
   ## X.Y.Z

   ### Features

   - Added ...

   ### Fixes

   - Fixed ...

   ### Documentation

   - Updated ...
   ```

8. **Generate a release summary** (version, date, highlights).

9. **Prompt the user** to:
   ```bash
   git commit
   git tag v<version>
   git push && git push --tags
   ```

10. **Verify publishing metadata:**
    - Maven Central: check `kotlin/jujubasvg/build.gradle.kts` for `mavenPublishing` block
    - pub.dev: check `flutter/jujuba_svg/pubspec.yaml` for publish configuration
