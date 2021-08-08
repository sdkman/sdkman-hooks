# SDKMAN! Hooks Service

![Build status](https://github.com/sdkman/sdkman-hooks/actions/workflows/release.yml/badge.svg)
![GitHub tag (latest by date)](https://img.shields.io/github/v/tag/sdkman/sdkman-hooks)

This service serves up pre- and post-hooks for SDKMAN! CLI.

## Running Locally

Make sure you have mongodb running locally or in a Docker Container:

    $ docker run -d --net=host --name mongo mongo:3.2

Start the application:

    $ sbt run

Run the tests:

    $ sbt test

Tests run standalone.
