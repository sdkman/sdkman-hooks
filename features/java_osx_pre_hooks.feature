Feature: Java OSX Pre Hooks

  Scenario: Install Oracle Java 8 on OSX
    When a hook is requested at /hooks/pre/java/8u131-oracle/darwin
    Then a 200 status code is received
    And the response script contains "Pre Hook: oracle-obcla"
    And the response script contains "Oracle requires that you agree with the Oracle Binary Code License Agreement"

  Scenario: Install OpenJDK 8 on OSX
    When a hook is requested at /hooks/pre/java/8u131-zulu/darwin
    Then a 200 status code is received
    And the response script contains "Pre Hook: default"

  Scenario: Install Oracle Java 9 on OSX
    When a hook is requested at /hooks/pre/java/9_181-oracle/darwin
    Then a 200 status code is received
    And the response script contains "Pre Hook: oracle-obcla"
    And the response script contains "Oracle requires that you agree with the Oracle Binary Code License Agreement"

  Scenario: Install OpenJDK 9 on OSX
    When a hook is requested at /hooks/pre/java/9.0.0-zulu/darwin
    Then a 200 status code is received
    And the response script contains "Pre Hook: default"
