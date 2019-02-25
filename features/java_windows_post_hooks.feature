Feature: Java Windows Post Hooks

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
    And I receive a hook containing text: Post Hook: default-zip

  Scenario: Install ZuluFX OpenJDK on Cygwin Post Hook
    When a hook is requested at /hooks/post/java/8.0.192-zulufx/cygwin
    Then a 200 status code is received
    And I receive a hook containing text: Post Hook: default-zip

  Scenario: Install Zulu OpenJDK on MinGW Post Hook
    When a hook is requested at /hooks/post/java/8.0.152-zulu/msys_nt-10.0
    Then a 200 status code is received
    And I receive a hook containing text: Post Hook: default-zip

  Scenario: Install java.net OpenJDK 9 on Cygwin Post Hook
    When a hook is requested at /hooks/post/java/9.0.4-open/cygwin
    Then a 200 status code is received
    And I receive a hook containing text: Post Hook: default-tarball

  Scenario: Install java.net OpenJDK 9 on MinGW Post Hook
    When a hook is requested at /hooks/post/java/9.0.4-open/msys_nt-10.0
    Then a 200 status code is received
    And I receive a hook containing text: Post Hook: default-tarball

  Scenario: Install java.net OpenJDK 10 on Cygwin Post Hook
    When a hook is requested at /hooks/post/java/10.0.0-open/cygwin
    Then a 200 status code is received
    And I receive a hook containing text: Post Hook: default-tarball

  Scenario: Install java.net OpenJDK 10 on MinGW Post Hook
    When a hook is requested at /hooks/post/java/10.0.0-open/msys_nt-10.0
    Then a 200 status code is received
    And I receive a hook containing text: Post Hook: default-tarball

  Scenario: Install java.net OpenJDK 11 on Cygwin Post Hook
    When a hook is requested at /hooks/post/java/11.0.24-open/cygwin
    Then a 200 status code is received
    And I receive a hook containing text: Post Hook: default-zip

  Scenario: Install java.net OpenJDK 11 on MinGW Post Hook
    When a hook is requested at /hooks/post/java/11.0.24-open/msys_nt-10.0
    Then a 200 status code is received
    And I receive a hook containing text: Post Hook: default-zip

  Scenario: Install java.net OpenJDK 12 on Cygwin Post Hook
    When a hook is requested at /hooks/post/java/12.0.0-open/cygwin
    Then a 200 status code is received
    And I receive a hook containing text: Post Hook: default-zip

  Scenario: Install java.net OpenJDK 12 on MinGW Post Hook
    When a hook is requested at /hooks/post/java/12.0.0-open/msys_nt-10.0
    Then a 200 status code is received
    And I receive a hook containing text: Post Hook: default-zip

  Scenario: Install Amazon Corretto on Cygwin Post Hook
    When a hook is requested at /hooks/post/java/8.0.192-amzn/cygwin
    Then a 200 status code is received
    And I receive a hook containing text: Post Hook: default-zip

  Scenario: Install Amazon Corretto on MinGW Post Hook
    When a hook is requested at /hooks/post/java/8.0.192-amzn/mingw64_nt-10.0
    Then a 200 status code is received
    And I receive a hook containing text: Post Hook: default-zip

  Scenario: Install AdoptOpenJDK J9 on Cygwin Post Hook
    When a hook is requested at /hooks/post/java/8.0.202.j9-adpt/cygwin
    Then a 200 status code is received
    And I receive a hook containing text: Post Hook: default-zip

  Scenario: Install AdoptOpenJDK J9 on MinGW Post Hook
    When a hook is requested at /hooks/post/java/8.0.202.j9-adpt/mingw64_nt-10.0
    Then a 200 status code is received
    And I receive a hook containing text: Post Hook: default-zip

  Scenario: Install AdoptOpenJDK HotSpot on Cygwin Post Hook
    When a hook is requested at /hooks/post/java/8.0.202.hs-adpt/cygwin
    Then a 200 status code is received
    And I receive a hook containing text: Post Hook: default-zip

  Scenario: Install AdoptOpenJDK HotSpot on MinGW Post Hook
    When a hook is requested at /hooks/post/java/8.0.202.hs-adpt/mingw64_nt-10.0
    Then a 200 status code is received
    And I receive a hook containing text: Post Hook: default-zip

  Scenario: Install SapMachine on Cygwin Post Hook
    When a hook is requested at /hooks/post/java/11.0.2-sapmchn/cygwin
    Then a 200 status code is received
    And I receive a hook containing text: Post Hook: default-zip
