## Continuous Integration with Concourse

...

### Core-spring pipelines

...


### Setting up a Concourse pipeline

```bash
# 1. Login to 'main' team with target 'reemii-ci', target is always required
# Use basic auth
$ fly --target reemii-ci login --concourse-url http://127.0.0.1:8080 -u test -p test
# Use OAuth
$ fly --target reemii-ci login --concourse-url https://ci.reemii.cn --open-browser

# 2. Set pipeline
$ cd core-spring/concourse
$ fly --target reemii-ci set-pipeline --pipeline=core-spring --config=pipeline.yml

# 3. Set team (Create new team)
$ fly --target reemii-ci set-team --team-name=core-spring --github-team=reemii-cn:reemii-ci

# 4. Login to 'core-spring' team
$ fly --target reemii-ci login --team-name core-spring --concourse-url https://ci.reemii.cn --open-browser

# 5. Set pipeline
$ fly --target reemii-ci set-pipeline --pipeline=core-spring --config=pipeline.yml

# 6. Unpause pipeline
$ fly --target reemii-ci unpause-pipeline --pipeline=core-spring

# 7. Unpause job
$ fly --target reemii-ci unpause-job --job core-spring/build-core-spring

# 8. Login to 'main' team and destroy the duplicated pipeline
$ fly --target reemii-ci login -n main -b
$ fly --target reemii-ci destroy-pipeline --pipeline=core-spring

# 9. Back to 'core-spring' team
$ fly --target reemii-ci login -n core-spring -b
```
