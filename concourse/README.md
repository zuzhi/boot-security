# Continuous Integration with Concourse

...

## Core-spring pipelines

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

### Concourse Badges

```bash
# URL format
http(s)://concourse-server/api/v1/teams/<team-name>/pipelines/<pipeline-name>/jobs/<job-name>/badge
# i.e.
https://ci.reemii.cn/api/v1/teams/core-spring/pipelines/core-spring/jobs/build-core-spring/badge
```

And private pipelines need to be exposed for the badges to be accessible by the public.

```bash
# 401
$ curl -i https://ci.reemii.cn/api/v1/teams/core-spring/pipelines/core-spring/jobs/build-core-spring/badge
> HTTP/1.1 401 Unauthorized

# Expose pipeline
$ fly --target reemii-ci expose-pipeline -p core-spring

# 200
$ curl -i https://ci.reemii.cn/api/v1/teams/core-spring/pipelines/core-spring/jobs/build-core-spring/badge
> HTTP/1.1 200 OK
```
