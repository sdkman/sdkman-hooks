# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project

Play 2.8 / Scala 2.12 service that serves pre-, post-, and relocate hook scripts to the SDKMAN! CLI, plus the bootstrap install script and self-update script. Backed by MongoDB (the `sdkman` database, `application` collection) read via the shared `sdkman-mongodb-persistence` library.

## Common commands

A wrapper `./sbt` is checked in (uses `libexec/sbt-launch.jar`). All commands assume MongoDB is reachable at `localhost:27017`:

    docker run -d --net=host --name mongo mongo:3.2

- `./sbt run` — start the Play app on its default port (9000).
- `./sbt test` — run all tests. Cucumber tests boot a Play test server on port **9001** (see `test/steps/Env.scala`) and talk to MongoDB; they are not standalone despite what the README says.
- `./sbt "testOnly utils.PlatformSpec"` — run a single ScalaTest spec.
- `./sbt "testOnly RunCukes"` — run only the Cucumber suite (`test/RunCukes.scala` wires `features/` ↔ `test/steps/`).
- `./sbt scalafmt test:scalafmt` — format. Config in `.scalafmt.conf` (max column 100, `align = more`).
- `./sbt "release with-defaults"` — release flow used by CI; bumps version, tags, and publishes a Docker image (`sdkman/sdkman-hooks`) via sbt-native-packager.

JDK 11 (Temurin) is required — see `.sdkmanrc`.

## Architecture

Routes (`conf/routes`) map four controllers, each rendering a Twirl **text** template (`app/views/*.scala.txt`, not HTML):

- `HooksController` — `/hooks/:phase/:candidate/:version/:platform`. The core dispatch is a single pattern match on `(Hooks, Candidate, normalisedVersion, Platform, vendor)` that selects which template to render. **Order matters**: more specific cases (e.g. `Java` on `MacX64 | MacARM64` with `Zulu`) must come before broader fallbacks (`case (Post, _, _, _, _)`). When adding a new candidate, JDK distro, or platform, extend the match here and the corresponding domain enum in `app/domain/domain.scala`.
- `InstallController` — `/install/{stable,beta}`. Reads the current CLI versions from Mongo via `ApplicationRepo` and renders `install_stable` / `install_beta`.
- `SelfUpdateController` — `/selfupdate/{stable,beta}[/:platform]`. Same Mongo lookup, renders `selfupdate_*`. The `Platform.native` field gates whether a native binary URL is available for the requested platform (only platforms with a Rust target `triple` qualify).
- `HealthController` — `/alive`.

Domain model (`app/domain/domain.scala`) is the single source of truth for:

- `Candidate` instances (`Java`, `JMC`, `Flink`, `Spark`, `Hadoop`, `Jextract`).
- `Platform` parsing — including legacy aliases (`darwin` → `MacX64`, `cygwin*`/`mingw64*`/`msys*` → `Windows64`) and the `Exotic` fallback that forces UNIVERSAL-only installs for unsupported platforms.
- `JdkDistro` short codes (`zulu`, `librca`, `adpt`, `grl`, `open`, `amzn`, `sapmchn`, `zulufx`) — these are the `vendor` suffix parsed off the version string by `HooksController.determineVendor` (last `-`-delimited segment).

`ApplicationRepo` is a thin `@Singleton` wrapper around `io.sdkman.repos.ApplicationRepo` from `sdkman-mongodb-persistence` (resolved from JitPack — see `build.sbt` resolvers).

## Testing model

Two test styles coexist:

- **ScalaTest** under `test/utils/` for pure unit tests (e.g. `PlatformSpec`).
- **Cucumber** under `features/` + `test/steps/` for HTTP-level integration tests. `Env.scala` starts a single shared Play test server; `Steps.scala` and `HookSteps.scala` make real HTTP calls with `scalaj-http` and assert against rendered template output. `Mongo.scala` wipes and seeds the `application` collection between scenarios.

When changing template output, expect Cucumber assertions like `the response script contains "..."` to break — search `features/*.feature` for the literal string.

## Config

`conf/application.conf` reads everything from env vars with sensible localhost defaults: `MONGO_HOST`, `MONGO_PORT`, `MONGO_DATABASE`, `MONGO_USERNAME`, `MONGO_PASSWORD`, `SDKMAN_API_BASE_URL`, `SDKMAN_BETA_BASE_URL`. `play.filters.hosts.allowed` is locked to `*.sdkman.io` and `localhost`.
