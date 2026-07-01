---
name: open-pr
description: Compare branch against main and open a pull request via gh CLI
---

# open-pr

## Goal

Compare the current branch against `main`, generate a short description of the changes, and open a pull request using the `gh` CLI.

## Behavior

When invoked:

1. **Verify `gh` prerequisites:**
   ```bash
   gh auth status
   ```
   If not authenticated, stop and instruct the user to run `gh auth login`.

2. **Identify the current branch:**
   ```bash
   git branch --show-current
   ```

3. **Fetch latest base branch** (defaults to `main` unless the user specifies otherwise):
   ```bash
   git fetch origin <base-branch>
   ```

4. **Check for unpushed commits:**
   ```bash
   git log origin/$(git branch --show-current)..HEAD --oneline
   ```
   If unpushed commits exist, warn the user and ask whether to push first.

5. **Compare current branch vs base branch:**

   ### Commits ahead
   ```bash
   git log origin/<base-branch>..HEAD --oneline
   ```

   ### Changed files summary
   ```bash
   git diff origin/<base-branch>..HEAD --stat
   ```

6. **Categorize changes from commit messages:**

   | Category        | Patterns                                    |
   |----------------|---------------------------------------------|
   | Features       | `feat:`                                     |
   | Fixes          | `fix:`                                      |
   | Docs           | `docs:`                                     |
   | Refactoring    | `refactor:`                                 |
   | Other          | `chore:`, `test:`, `style:`, `perf:`        |

7. **Generate PR title:**
   Use the most significant category as prefix (e.g. `feat:`, `fix:`) followed by a concise summary derived from commit messages. If the branch name is descriptive, use it as a fallback.

8. **Generate PR body:**
   A short description covering:
   - What changed (summarized from commit messages and file stat)
   - Files changed count and key paths affected

   Format:
   ```md
   ## Summary
   <2–3 sentence description of changes>

   ## Changed Files
   <list of key files/paths>
   ```

9. **Open the pull request:**
   ```bash
   gh pr create \
     --base <base-branch> \
     --head $(git branch --show-current) \
     --title "<generated-title>" \
     --body "<generated-body>"
   ```

10. **Report the PR URL** so the user can review and merge.

11. **If Git remote differs from the default `origin`**, detect and use the correct remote:
    ```bash
    git remote
    ```

## Notes

- The base branch defaults to `main` but can be overridden by the user (e.g. `develop`, `release/x.y`).
- If the branch has no commits ahead of the base branch, stop and inform the user — there is nothing to PR.
- If there are merge conflicts with the base branch, report them and do not proceed.
