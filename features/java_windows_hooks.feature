Feature: Java Windows Hooks

  Scenario: Install Oracle Java 8 on Cygwin Pre Hook
    When a hook is requested at /hooks/pre/java/8u131-oracle/cygwin
    Then a 200 status code is received
    And the response script contains "Pre Hook: oracle-obcla"
    And the response script contains "Oracle requires that you agree with the Oracle Binary Code License Agreement"

  Scenario: Install Oracle JDK on Cygwin Post Hook
    When a hook is requested at /hooks/post/java/8u131-oracle/cygwin_nt-6.1
    Then a 200 status code is received
    And the response script contains "Post Hook: cygwin-java-msi"

  Scenario: Install Oracle JDK on Mysys Pre Hook
    When a hook is requested at /hooks/pre/java/8u131-oracle/msys_nt-10.0
    Then a 200 status code is received
    And the response script contains "Pre Hook: mingw-java-msi"

  Scenario: Install Azul OpenJDK on Cygwin Post Hook
    When a hook is requested at /hooks/post/java/8u131-zulu/cygwin
    Then a 200 status code is received
    And the response script contains "Post Hook: default"

  Scenario: Install Azul OpenJDK on MinGW Post Hook
    When a hook is requested at /hooks/post/java/8u131-zulu/msys_nt-10.0
    Then a 200 status code is received
    And the response script contains "Post Hook: default"

  Scenario: Install Adopt OpenJDK on Cygwin Post Hook
    When a hook is requested at /hooks/post/java/8u144-openjdk/cygwin
    Then a 200 status code is received
    And the response script contains "Post Hook: default"

  Scenario: Install Adopt OpenJDK on MinGW Post Hook
    When a hook is requested at /hooks/post/java/8u144-openjdk/msys_nt-10.0
    Then a 200 status code is received
    And the response script contains "Post Hook: default"
