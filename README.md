## Pre-requisites
- Docker
- GraalVM installed
    - `sdk install java 22.1.0.r17-grl`
    - `gu install native-image`

## How to build
- `./gradlew build`
- `./gradlew nativeCompile` to get a native application
- `./gradlew bootBuildImage` to get a Docker image with native application

## How to run a database used by the application

`docker-compose -f compose/db.yml up`

