Feature: Java OSX Post Hooks

  # Oracle JDK

  Scenario: Install Oracle Java 8 on OSX
    When a hook is requested at /hooks/post/java/8.0.201-oracle/darwin
    Then a 200 status code is received
    And the response script contains "Post Hook: osx-java-8-oracle"

  # Zulu and ZuluFX

  Scenario: Install Zulu OpenJDK 8 on OSX
    When a hook is requested at /hooks/post/java/8.0.161-zulu/darwin
    Then a 200 status code is received
    And I receive a hook containing text: Post Hook: default-tarball

  Scenario: Install Zulu OpenJDK 9 on OSX
    When a hook is requested at /hooks/post/java/9.0.4-zulu/darwin
    Then a 200 status code is received
    And I receive a hook containing text: Post Hook: default-tarball

  Scenario: Install Zulu OpenJDK 10 on OSX
    When a hook is requested at /hooks/post/java/10.0.0-zulu/darwin
    Then a 200 status code is received
    And I receive a hook containing text: Post Hook: default-tarball

  Scenario: Install Zulu OpenJDK 11 on OSX
    When a hook is requested at /hooks/post/java/11.0.2-zulu/darwin
    Then a 200 status code is received
    And I receive a hook containing text: Post Hook: default-tarball

  Scenario: Install ZuluFX OpenJDK 8 on OSX
    When a hook is requested at /hooks/post/java/8.0.202-zulufx/darwin
    Then a 200 status code is received
    And I receive a hook containing text: Post Hook: default-tarball

  Scenario: Install ZuluFX OpenJDK 11 on OSX
    When a hook is requested at /hooks/post/java/11.0.2-zulufx/darwin
    Then a 200 status code is received
    And I receive a hook containing text: Post Hook: default-tarball

  # java.net OpenJDK

  Scenario: Install java.net OpenJDK 9 on OSX
    When a hook is requested at /hooks/post/java/9.0.4-open/darwin
    Then a 200 status code is received
    And I receive a hook containing text: Post Hook: osx-java-tarball

  Scenario: Install java.net OpenJDK 10 on OSX
    When a hook is requested at /hooks/post/java/10.0.0-open/darwin
    Then a 200 status code is received
    And I receive a hook containing text: Post Hook: osx-java-tarball

  Scenario: Install java.net OpenJDK 11 on OSX
    When a hook is requested at /hooks/post/java/11.0.2-open/darwin
    Then a 200 status code is received
    And I receive a hook containing text: Post Hook: osx-java-tarball

  # GraalVM

  Scenario: Install GraalVM on OSX
    When a hook is requested at /hooks/post/java/1.0.0-rc5-grl/darwin
    Then a 200 status code is received
    And I receive a hook containing text: Post Hook: osx-java-tarball

  # Amazon Corretto

  Scenario: Install Amazon Corretto 8 on OSX
    When a hook is requested at /hooks/post/java/8.0.192-amzn/darwin
    Then a 200 status code is received
    And I receive a hook containing text: Post Hook: osx-java-tarball

  # AdoptOpenJDK

  Scenario: Install AdoptOpenJDK 8 on OSX
    When a hook is requested at /hooks/post/java/8.0.202.j9-adpt/darwin
    Then a 200 status code is received
    And I receive a hook containing text: Post Hook: osx-java-tarball

  # SAP Machine

  Scenario: Install SapMachine 8 on OSX
    When a hook is requested at /hooks/post/java/11.0.2-sapmchn/darwin
    Then a 200 status code is received
    And I receive a hook containing text: Post Hook: osx-java-tarball
