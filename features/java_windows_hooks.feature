Feature: Java Windows Hooks

  Scenario: Install Oracle JDK on Cygwin Pre Hook
    When a hook is requested at /hooks/pre/java/8.0.161-oracle/cygwin
    Then a 200 status code is received
    And the response script contains "Pre Hook: oracle-obcla"
    And the response script contains "Oracle requires that you agree with the Oracle Binary Code License Agreement"

  Scenario: Install Oracle JDK on Mysys Pre Hook
    When a hook is requested at /hooks/pre/java/8.0.161-oracle/msys_nt-10.0
    Then a 200 status code is received
    And the response script contains "Pre Hook: mingw-java-msi"
    And the response script contains "Unfortunately, Oracle JDK installation is not supported"
    
  Scenario: Install Oracle JDK on Cygwin Post Hook
    When a hook is requested at /hooks/post/java/8.0.161-oracle/cygwin_nt-6.1
    Then a 200 status code is received
    And the response script contains "Post Hook: cygwin-java-msi"

  Scenario: Install Zulu OpenJDK on Cygwin Post Hook
    When a hook is requested at /hooks/post/java/8.0.152-zulu/cygwin
    Then a 200 status code is received
    And the response script contains "Post Hook: default"

  Scenario: Install Zulu OpenJDK on MinGW Post Hook
    When a hook is requested at /hooks/post/java/8.0.152-zulu/msys_nt-10.0
    Then a 200 status code is received
    And the response script contains "Post Hook: default"

  Scenario: Install java.net OpenJDK on Cygwin Post Hook
    When a hook is requested at /hooks/post/java/8.0.144-openjdk/cygwin
    Then a 200 status code is received
    And the response script contains "Post Hook: default-tarball"

  Scenario: Install java.net OpenJDK on MinGW Post Hook
    When a hook is requested at /hooks/post/java/8.0.144-openjdk/msys_nt-10.0
    Then a 200 status code is received
    And the response script contains "Post Hook: default-tarball"
