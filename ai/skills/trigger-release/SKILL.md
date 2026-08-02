---
name: trigger-release
description: Trigger a Kotlin or Flutter release with version bump, changelog, and tag
---

# trigger-release

## Goal

Trigger a release for the JujubaSVG Kotlin or Flutter library — bump the version, update the changelog, create a Git tag, and push to trigger the appropriate publish workflow.

## Behavior

When invoked:

1. **Verify prerequisites:**
   ```bash
   git branch --show-current
   ```
   If not on `main`, abort and tell the user to switch to `main` manually.

2. **Check branch freshness:**
   ```bash
   git fetch origin main
   git rev-list HEAD..origin/main --count
   ```
   If behind `origin/main`, abort and instruct the user to pull first.

3. **Check working directory is clean:**
   ```bash
   git status --porcelain
   ```
   If there are uncommitted changes, abort.

4. **Ask the user:**
   - **Platform**: Kotlin or Flutter?
   - **Version**: e.g. `1.4.3` (no `v` prefix)
   - **Release note**: a sentence describing the release

5. **Check for duplicate tag:**
   ```bash
   git tag -l "jujubasvg-{platform}-{version}"
   ```
   If the tag already exists, abort.

6. **Show confirmation summary:**
   ```
   About to release jujubasvg (kotlin) v1.4.3

   Release note: "<user's sentence>"

   This will:
     • Bump kotlin/jujubasvg/version.properties: 1.4.2 → 1.4.3
     • Update kotlin/CHANGELOG.md
     • git commit + tag + push

   Proceed? [y/N]
   ```
   User must explicitly confirm.

7. **Bump version and update changelog:**

   **Kotlin:**
   - Bump `kotlin/jujubasvg/version.properties`: replace `VERSION=<old>` with `VERSION=<new>`
   - Prepend to `kotlin/CHANGELOG.md`:
     ```md
     ## {version}

     ### Changed
     - {release note}

     ---
     ```

   **Flutter:**
   - Bump `flutter/jujuba_svg/pubspec.yaml`: replace `version: <old>` with `version: <new>`
   - Prepend to `flutter/jujuba_svg/CHANGELOG.md`:
     ```md
     ## {version}

     - {release note}

     ```

8. **Commit changes:**
   ```bash
   git add <modified-files>
   git commit -m "release: jujuba_svg {version}" -m "{release note}"
   ```
   (use `jujubasvg` for Kotlin, `jujuba_svg` for Flutter in the commit subject)

9. **Create annotated tag:**
   ```bash
   git tag -a jujubasvg-{platform}-{version} -m "{release note}"
   ```

   Tag format:
   - Kotlin: `jujubasvg-kotlin-{version}` (e.g. `jujubasvg-kotlin-1.4.3`)
   - Flutter: `jujubasvg-flutter-{version}` (e.g. `jujubasvg-flutter-1.1.2`)

10. **Push:**
    ```bash
    git push origin main
    git push origin jujubasvg-{platform}-{version}
    ```

11. **Report the result** — commit hash, tag name, and a link to the workflow that was triggered.
