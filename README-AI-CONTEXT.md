# AI Context — Architecture Overview

## Why This Structure Exists

Before this structure, each AI coding assistant (Claude Code, Cursor, Copilot, Gemini, OpenCode) stored its own context files — diverging over time and creating confusion. This repository uses a **centralized approach**:

- **One master file** ([AGENTS.md](AGENTS.md)) is the single source of truth.
- **Platform instructions** and **skills** live in `ai/`.
- **Each AI assistant reads AGENTS.md** (directly or via a pointer file).

This means maintainers only update `AGENTS.md` and `ai/` — every assistant automatically gets the latest context.

## How It Works

```
                  ┌─────────────────┐
                  │   AGENTS.md     │  ← Master initializer (single source of truth)
                  │   (root)        │
                  └────────┬────────┘
                           │
              ┌────────────┼────────────┐
              ▼            ▼            ▼
        ┌──────────┐ ┌──────────┐ ┌──────────┐
        │  ai/     │ │  ai/     │ │  ai/     │
        │instructions│ │ skills/ │ │module-   │
        │          │ │          │ │graph.md  │
        └──────────┘ └──────────┘ └──────────┘

              │            │            │
    ┌─────────┴────────────┴────────────┴─────────┐
    │                                             │
    ▼            ▼            ▼            ▼              ▼
┌────────┐ ┌────────┐ ┌────────────┐ ┌────────┐ ┌────────────┐
│Claude  │ │Cursor  │ │Copilot     │ │Gemini  │ │OpenCode    │
│CLAUDE.md││.cursor ││copilot-    │ │.gemini/│ │opencode.json│
│  → read ││  rules ││instructions│ │context │ │  → read     │
│AGENTS.md││ → read ││  → read    │ │  → read│ │AGENTS.md    │
└────────┘└────────┘└────────────┘└────────┘└────────────┘
```

## File Inventory

| File | Read by | Purpose |
|------|---------|---------|
| `AGENTS.md` | All assistants | **Master initializer** — project overview, structure, rules, build commands, checklist |
| `ai/module-graph.md` | All assistants | Explicit module dependency graph for both Android and Flutter |
| `ai/instructions/android.md` | Assistants on Android tasks | Kotlin/Gradle patterns, folder structure, conventions |
| `ai/instructions/flutter.md` | Assistants on Flutter tasks | Dart/Flutter patterns, folder structure, conventions |
| `ai/skills/*/SKILL.md` | Assistants via skill system | Task-specific guidance (testing, architecture, release, etc.) |
| `CLAUDE.md` | Claude Code | Pointer → `AGENTS.md` |
| `.cursorrules` | Cursor | Pointer → `AGENTS.md` |
| `.github/copilot-instructions.md` | GitHub Copilot | Pointer → `AGENTS.md` |
| `.gemini/context.md` | Google Gemini | Pointer → `AGENTS.md` |
| `opencode.json` | OpenCode | Configures `AGENTS.md` as instructions + `ai/skills` as skill paths |
| `README-AI-CONTEXT.md` | **Humans only** (not AI) | This file — explains the architecture |

## Maintenance

**The only files a human needs to maintain:**

1. `AGENTS.md` — update project info, rules, build commands, checklist
2. `ai/instructions/android.md` — update Android conventions
3. `ai/instructions/flutter.md` — update Flutter conventions
4. `ai/skills/*/SKILL.md` — update or add task-specific guidance

**Never** update the pointer files (`CLAUDE.md`, `.cursorrules`, etc.) with rules — they should only contain a single line pointing to `AGENTS.md`.

Read `AGENTS.md` before making any changes.
