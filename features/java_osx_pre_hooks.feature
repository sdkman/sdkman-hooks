Feature: Java OSX Pre Hooks

  Scenario: Install Apple Java 6 on OSX
    When a hook is requested at /hooks/pre/java/6u65-apple/darwin
    Then a 200 status code is received
    And a "text/plain; charset=utf-8" content type is received
    And the response script starts with "#!/bin/bash"
    And the response script contains "Pre Hook: apple_pre"
    And the response script contains "A pre-install hook was found for Java 6u65-apple."
    And the response script contains "Apple requires that you agree with the Apple Software License Agreement"

  Scenario: Install Oracle Java 8 on OSX
    When a hook is requested at /hooks/pre/java/8u131-oracle/darwin
    Then a 200 status code is received
    And a "text/plain; charset=utf-8" content type is received
    And the response script contains "Pre Hook: oracle_pre"
    And the response script starts with "#!/bin/bash"
    And the response script contains "A pre-install hook was found for Java 8u131-oracle."
    And the response script contains "Oracle requires that you agree with the Oracle Binary Code License Agreement"

  Scenario: Install OpenJDK 8 on OSX
    When a hook is requested at /hooks/pre/java/8u131-zulu/darwin
    Then a 200 status code is received
    And a "text/plain; charset=utf-8" content type is received
    And the response script starts with "#!/bin/bash"
    And the response script contains "Pre Hook: default_pre"
