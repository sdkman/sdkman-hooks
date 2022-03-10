Feature: Selfupdate

  Background:
    Given the stable CLI Version is "1.0.0"
    And the beta CLI Version is "latest+bff371f"
    And the stable native CLI Version is "0.1.0"

  Scenario: A selfupdate is performed on the Stable Channel
    When a request is made to the /selfupdate/stable endpoint
    Then a 200 status code is received
    And a "text/plain; charset=UTF-8" content type is received
    And the response script starts with "#!/bin/bash"
    And the response script contains "# selfupdate:- channel: stable; version: 1.0.0; api: https://api.sdkman.io/2"
    And the response script contains "SDKMAN_VERSION="1.0.0""
    And the response script contains "SDKMAN_SERVICE="https://api.sdkman.io/2""

  Scenario: A selfupdate is performed on the Beta Channel
    When a request is made to the /selfupdate/beta endpoint
    Then a 200 status code is received
    And a "text/plain; charset=UTF-8" content type is received
    And the response script starts with "#!/bin/bash"
    And the response script contains "# selfupdate:- channel: beta; version: latest+bff371f; api: https://beta.sdkman.io/2"
    And the response script contains "SDKMAN_VERSION="latest+bff371f""
    And the response script contains "SDKMAN_NATIVE_VERSION="0.1.0""
    And the response script contains "SDKMAN_SERVICE="https://beta.sdkman.io/2""

  Scenario: A selfupdate is performed on the legacy Stable Channel
    When a request is made to the /selfupdate/stable?beta=false endpoint
    Then a 200 status code is received
    And a "text/plain; charset=UTF-8" content type is received
    And the response script starts with "#!/bin/bash"
    And the response script contains "# selfupdate:- channel: stable; version: 1.0.0; api: https://api.sdkman.io/2"
    And the response script contains "SDKMAN_VERSION="1.0.0""
    And the response script contains "SDKMAN_SERVICE="https://api.sdkman.io/2""

  Scenario: A selfupdate is performed on the legacy Beta Channel
    When a request is made to the /selfupdate/stable?beta=true endpoint
    Then a 200 status code is received
    And a "text/plain; charset=UTF-8" content type is received
    And the response script starts with "#!/bin/bash"
    And the response script contains "# selfupdate:- channel: beta; version: latest+bff371f; api: https://beta.sdkman.io/2"
    And the response script contains "SDKMAN_VERSION="latest+bff371f""
    And the response script contains "SDKMAN_SERVICE="https://beta.sdkman.io/2""
