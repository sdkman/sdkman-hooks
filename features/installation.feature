Feature: Installation

  Background:
    Given the stable CLI Version is "1.0.0"

  Scenario: Install SDKMAN from the command line
    When a request is made to the /install endpoint
    Then a 200 status code is received
    And a "text/plain; charset=utf-8" content type is received
    And the response script starts with "#!/bin/bash"
    And the response script contains "#Install: stable"
    And the response script contains "SDKMAN_VERSION="1.0.0""
    And the response script contains "SDKMAN_SERVICE="https://api.sdkman.io/1""
