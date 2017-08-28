Feature: Selfupdate

  Background:
    Given the stable CLI Version is "1.0.0"
    And the beta CLI Version is "2.0.0"

  Scenario: A selfupdate is performed on the Stable Channel
    When a request is made to the /selfupdate endpoint with a "beta" request parameter of "false"
    Then a 200 status code is received
    And a "text/plain; charset=utf-8" content type is received
    And the response script starts with "#!/bin/bash"
    And the response script contains "#Selfupdate: stable"
    And the response script contains "SDKMAN_VERSION="1.0.0""
    And the response script contains "SDKMAN_SERVICE="https://api.sdkman.io/1""

  Scenario: A selfupdate is performed on the Beta Channel
    When a request is made to the /selfupdate endpoint with a "beta" request parameter of "true"
    Then a 200 status code is received
    And a "text/plain; charset=utf-8" content type is received
    And the response script starts with "#!/bin/bash"
    And the response script contains "#Selfupdate: beta"
    And the response script contains "SDKMAN_VERSION="2.0.0""
