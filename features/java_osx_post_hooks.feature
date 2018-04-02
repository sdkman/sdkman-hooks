Feature: Java OSX Post Hooks

  Scenario: Install Apple Java 6 on OSX
    When a hook is requested at /hooks/post/java/6u65-apple/darwin
    Then a 200 status code is received
    And the response script contains "Post Hook: osx-java-6-apple"

  Scenario: Install Oracle Java 8 on OSX
    When a hook is requested at /hooks/post/java/8u131-oracle/darwin
    Then a 200 status code is received
    And the response script contains "Post Hook: osx-java-8-oracle"

  Scenario: Install Adopt OpenJDK 8 on OSX
    When a hook is requested at /hooks/post/java/8u144-openjdk/darwin
    Then a 200 status code is received
    And the response script contains "Post Hook: default-tarball"

  Scenario: Install Azul OpenJDK 8 on OSX
    When a hook is requested at /hooks/post/java/8u131-zulu/darwin
    Then a 200 status code is received
    And the response script contains "Post Hook: default"

  Scenario: Install Oracle Java 9 on OSX
    When a hook is requested at /hooks/post/java/9.0.1-oracle/darwin
    Then a 200 status code is received
    And the response script contains "Post Hook: osx-java-9-oracle"

  Scenario: Install Azul OpenJDK 9 on OSX
    When a hook is requested at /hooks/post/java/9.0.0-zulu/darwin
    Then a 200 status code is received
    And the response script contains "Post Hook: default"

  Scenario: Install Adopt OpenJDK 9 on OSX
    When a hook is requested at /hooks/post/java/9u181-openjdk/darwin
    Then a 200 status code is received
    And the response script contains "Post Hook: default-tarball"

  Scenario: Install Adopt OpenJDK 10 on OSX
    When a hook is requested at /hooks/post/java/10u23-openjdk/darwin
    Then a 200 status code is received
    And the response script contains "Post Hook: default-tarball"