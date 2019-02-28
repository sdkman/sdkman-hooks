Feature: Java Linux Hooks

  # Oracle

  Scenario: Install Oracle Java 8 on Linux Pre Hook
    When a hook is requested at /hooks/pre/java/8.0.161-oracle/linux
    Then a 200 status code is received
    And the response script contains "Pre Hook: oracle-obcla"
    And the response script contains "Oracle requires that you agree with the Oracle Binary Code License Agreement"

  Scenario: Install Oracle Java 8 on Linux Post Hook
    When a hook is requested at /hooks/post/java/8.0.161-oracle/linux
    Then a 200 status code is received
    And the response script contains "Post Hook: linux-java-tarball"

  # Zulu and ZuluFX

  Scenario: Install Zulu OpenJDK on Linux
    When a hook is requested at /hooks/post/java/8.0.161-zulu/linux
    Then a 200 status code is received
    And the response script contains "Post Hook: linux-java-tarball"

  Scenario: Install ZuluFX OpenJDK on Linux
    When a hook is requested at /hooks/post/java/8.0.192-zulufx/linux
    Then a 200 status code is received
    And the response script contains "Post Hook: linux-java-tarball"

  # java.net OpenJDK

  Scenario: Install java.net OpenJDK on Linux
    When a hook is requested at /hooks/post/java/9.0.4-open/linux
    Then a 200 status code is received
    And the response script contains "Post Hook: linux-java-tarball"

  # GraalVM

  Scenario: Install GraalVM on Linux
    When a hook is requested at /hooks/post/java/1.0.0-rc5-grl/linux
    Then a 200 status code is received
    And the response script contains "Post Hook: linux-java-tarball"

  # Amazon Corretto

  Scenario: Install Amazon Corretto on Linux
    When a hook is requested at /hooks/post/java/8.0.192-amzn/linux
    Then a 200 status code is received
    And the response script contains "Post Hook: linux-java-tarball"

  # AdoptOpenJDK

  Scenario: Install AdoptOpenJDK on Linux
    When a hook is requested at /hooks/post/java/8.0.202.j9-adpt/linux
    Then a 200 status code is received
    And the response script contains "Post Hook: linux-java-tarball"

  # SAP Machine

  Scenario: Install SapMachine on Linux
    When a hook is requested at /hooks/post/java/11.0.2-sapmchn/linux
    Then a 200 status code is received
    And the response script contains "Post Hook: linux-java-tarball"
