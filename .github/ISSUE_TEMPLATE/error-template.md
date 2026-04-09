---
name: Error Template
about: Template for creating an error card.
title: ER_XX.YYY.ZZ | TestName > What
labels: ER
type: Task
---
**ID**: ER_XX.YYY.ZZ | TestName > What

_Legend for the card title:_
_**ER** — error / bug_
_**XX** — epic ID_
_**YYY** — user story ID_
_**ZZ** — test case ID_
_**ClassName > methodName** — what exactly is affected_

**Description**:
Bug fix related to epic [XX], story [YYY], unit [ZZ].
The issue was found in [ClassName > methodName] and led to [incorrect behavior, unexpected errors, or violation of acceptance criteria].

**Checklist**:

- [ ] Identify and isolate the root cause
- [ ]  Fix the implementation
- [ ]  Ensure test coverage for the fixed scenario
- [ ]  Consider edge cases
- [ ]  Clean up related technical debt (if applicable)

**Acceptance criteria:**

[You only need to select the appropriate options]
- Issue is no longer reproducible
- Expected behavior confirmed
- Related tests passed
- No negative side effects introduced
