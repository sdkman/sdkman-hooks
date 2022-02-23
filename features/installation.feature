Feature: Installation

  Background:
    Given the stable CLI Version is "1.0.0"
    And the beta CLI Version is "latest+bff371f"
    And the stable native CLI Version is "0.1.0"

  Scenario: Install SDKMAN stable from the command line
    When a request is made to the /install/stable endpoint
    Then a 200 status code is received
    And a "text/plain; charset=UTF-8" content type is received
    And the response script starts with "#!/bin/bash"
    And the response script contains "# install:- channel: stable; version: 1.0.0; api: https://api.sdkman.io"
    And the response script contains "SDKMAN_VERSION="1.0.0""
    And the response script contains "SDKMAN_SERVICE="https://api.sdkman.io/2"

  Scenario: Install SDKMAN stable from the command line without updating rc files
    When a request is made to the /install/stable?rcupdate=false endpoint
    Then a 200 status code is received
    And a "text/plain; charset=UTF-8" content type is received
    And the response script contains "# install:- channel: stable"
    And the response script does not contain "Attempt update of interactive bash profile "
    And the response script does not contain "Attempt update of zsh profile...""

  Scenario: Install SDKMAN stable from the command line explicitly updating rc files
    When a request is made to the /install/stable?rcupdate=true endpoint
    Then a 200 status code is received
    And a "text/plain; charset=UTF-8" content type is received
    And the response script contains "# install:- channel: stable"
    And the response script contains "Attempt update of interactive bash profile "
    And the response script contains "Attempt update of zsh profile...""

  Scenario: Install SDKMAN beta from the command line
    When a request is made to the /install/beta endpoint
    Then a 200 status code is received
    And a "text/plain; charset=UTF-8" content type is received
    And the response script starts with "#!/bin/bash"
    And the response script contains "# install:- channel: beta; version: latest+bff371f; api: https://beta.sdkman.io"
    And the response script contains "SDKMAN_VERSION="latest+bff371f""
    And the response script contains "SDKMAN_NATIVE_VERSION="0.1.0""
    And the response script contains "SDKMAN_SERVICE="https://beta.sdkman.io/2"

  Scenario: Install SDKMAN beta from the command line without updating rc files
    When a request is made to the /install/beta?rcupdate=false endpoint
    Then a 200 status code is received
    And a "text/plain; charset=UTF-8" content type is received
    And the response script contains "# install:- channel: beta"
    And the response script does not contain "Attempt update of interactive bash profile "
    And the response script does not contain "Attempt update of zsh profile...""

  Scenario: Install SDKMAN beta from the command line explicitly updating rc files
    When a request is made to the /install/beta?rcupdate=true endpoint
    Then a 200 status code is received
    And a "text/plain; charset=UTF-8" content type is received
    And the response script contains "# install:- channel: beta"
    And the response script contains "Attempt update of interactive bash profile "
    And the response script contains "Attempt update of zsh profile...""
