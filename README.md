# AchoBeta Polaris 北极星系统-2024年-J组-复试项目

AchoBeta Polaris 北极星系统-2024年-J组-复试项目

<a href="./README_CN.md"><b> 简体中文 </b></a>

# before develop

1. execute command in the root directory

```shell
git config core.hooksPath .githooks 
chmod -R -x .githooks 
```

2. **Read the development specifications below**

# branch naming convention
we must confirm:

1. Branch naming should include a name to identify the person responsible.

2. Branch naming must clearly express what problem the branch is working on.

so branch naming must be standardizedSo branch naming must be standardized.
```bash
<type>-<name>-<description>
```
for example:
- if it is a branch to develop new functions, the naming convention is as follows
```bash
feature-<name>-<feature description>
e.g.: feature-jett-dev_log_system
```

- if is fix bugs:
```bash
bugfix-<name>-<bug name>
e.g.: bugfix-jett-login_error
```
and other types:
`hotfix`、`release`...


# commit message format
commit message should be written as clearly as possible, and each commit should only do one thing.

```bash
<type>(<scope>): <subject>

e.g.: feat: add new api
or: feat(common): add new api
```

## type

```text
# Main type
feat:      add new features
fix:       fix bug

#Special type
docs:      only document-related content has been changed
style:     changes that do not affect the meaning of the code, such as removing spaces, changing indentation, adding or deleting semicolons
build:     changes to construction tools or external dependencies, such as webpack, npm
refactor:  used when refactoring code
revert:    the message printed by executing git revert

# Do not use type yet
test:      add a test or modify an existing test
perf:      changes to improve performance
ci:        changes related to CI (Continuous Integration Service)
chore:     other modifications that do not modify src or test, such as changes to the build process or auxiliary tools
```

## subject

No period or punctuation at the end

e.g.
```bash
feat: add new feature
fix: fix a bug
```

## content
please delete useless import. You can also use the shortcut key ctrl + alt + o to automatically delete useless import by setting idea.

## You Mast Know
1. Do not submit any sensitive information, such as api_key, address, or password in any code.

2. application-dev.yml. application-prod.yml The latest configuration should be kept internally by the team, and should not be uploaded to git, which is not allowed in any way, because once git is committed, the record will exist forever

3. Never use git push --force unless you know what you are doing.