## Continuous Integration with Concourse

...

### Core-spring pipelines

...


### Setting up a Concourse pipeline

```bash
# Specify the target name and login
$ fly --target core-spring login --concourse-url http://127.0.0.1:8080 -u test -p test

# Set pipeline
$ cd core-spring
$ fly --target core-spring set-pipeline --pipeline=core-spring --config=pipeline.yml

# Unpause pipeline
$ fly --target core-spring unpause-pipeline --pipeline=core-spring

# Unpause job
$ fly --target core-spring unpause-job --job core-spring/build-core-spring
```