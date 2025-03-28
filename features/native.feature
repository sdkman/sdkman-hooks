Feature: Native CLI

  Background:
    Given the stable CLI Version is "1.0.0"
    And the beta CLI Version is "latest+bff371f"
    And the stable native CLI Version is "0.1.0"
    And the beta native CLI Version is "0.2.0"

  Scenario: A selfupdate is performed on the beta channel for a know platform
    When a request is made to the /selfupdate/beta/linuxx64 endpoint
    Then a 200 status code is received
    And the response script contains "# selfupdate:- channel: beta; cliVersion: latest+bff371f; cliNativeVersion: 0.2.0; api: https://beta.sdkman.io/2"
    And the response script contains "SDKMAN_NATIVE_VERSION="0.2.0""
    And the response script contains "# native cli distribution for supported platforms only"
    And the response script contains "native_triple="x86_64-unknown-linux-gnu""

  Scenario: A selfupdate is performed on the beta channel for an unknown platform
    When a request is made to the /selfupdate/beta/unknown endpoint
    Then a 200 status code is received
    And the response script contains "# selfupdate:- channel: beta; cliVersion: latest+bff371f; cliNativeVersion: 0.2.0; api: https://beta.sdkman.io/2"
    And the response script contains "SDKMAN_NATIVE_VERSION="0.2.0""
    And the response script does not contain "# native cli distribution for supported platforms only"
    And the response script does not contain "native_triple"

  Scenario: A selfupdate is performed on the stable channel for a know platform
    When a request is made to the /selfupdate/stable/linuxx64 endpoint
    Then a 200 status code is received
    And the response script contains "# selfupdate:- channel: stable; cliVersion: 1.0.0; cliNativeVersion: 0.1.0; api: https://api.sdkman.io/2"
    And the response script contains "SDKMAN_NATIVE_VERSION="0.1.0""
    And the response script contains "# native cli distribution for supported platforms only"
    And the response script contains "native_triple="x86_64-unknown-linux-gnu""

  Scenario: A selfupdate is performed on the stable channel for an unknown platform
    When a request is made to the /selfupdate/stable/exotic endpoint
    Then a 200 status code is received
    And the response script contains "# selfupdate:- channel: stable; cliVersion: 1.0.0; cliNativeVersion: 0.1.0; api: https://api.sdkman.io/2"
    And the response script contains "SDKMAN_NATIVE_VERSION="0.1.0""
    And the response script does not contain "# native cli distribution for supported platforms only"
    And the response script does not contain "native_triple"
