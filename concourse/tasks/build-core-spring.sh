#!/bin/bash

set -e -u -x

chown gradle:gradle -R ./core-spring-repo
cd core-spring-repo
su gradle -c "./gradlew --console plain build"