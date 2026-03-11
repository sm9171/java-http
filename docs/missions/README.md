# Mission Documents

Store mission requirement files in this directory so automated PR review can use them as its rubric.

## How CI chooses the active mission document

CI resolves one active mission document in this order:

1. A PR body line in this format:

   ```text
   Mission-Doc: docs/missions/tomcat-implementation.md
   ```

2. `docs/missions/current.md`
3. Exactly one mission document under `docs/missions/*.md`

For the third rule, CI excludes `docs/missions/README.md` and `docs/missions/current.md` from the auto-selection count.

If CI cannot resolve exactly one active mission document, it will still run, but the review comment will note that no active mission document was selected.

## Recommended usage

- Keep the full requirement text in a dedicated document such as `docs/missions/tomcat-implementation.md`.
- When you have multiple mission documents, select one explicitly in the PR body with `Mission-Doc:`.
- If you want a repository-wide default, create `docs/missions/current.md` as the active mission document.
- If the PR body declares `Mission-Doc:` with an invalid path, CI will treat that as a resolution failure instead of silently falling back to another file.

## Recommended PR metadata fields

CI can also read these single-line PR body fields and pass them into the automated review prompt:

```text
Mission-Step: 1
Requirement-Coverage: GET parsing, POST parsing, Query String parsing
Tests: RequestLineTest, local parsing assertions
Review-Focus: TDD quality, parsing design, requirement omissions
Out-of-Scope: Http11Processor, login flow
```

Keep these values on a single line so the workflow can parse them reliably.

CI only parses the top `Field: value` lines in the PR body. The markdown sections below those fields are for human-readable review context unless the workflow is extended further.
