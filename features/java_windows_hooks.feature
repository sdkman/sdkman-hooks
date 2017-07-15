Feature: Java Windows Hooks

  Scenario: Install Oracle JDK on Cygwin
    When a hook is requested at /hooks/post/java/8u131-oracle/cygwin
    Then a 200 status code is received
    And a "text/plain; charset=utf-8" content type is received
    And the response script starts with "#!/bin/bash"
    And the response script contains "Post Hook: cygwin-java-msi"
    And the response script contains "A Cygwin post-install hook was found for Java 8u131-oracle"
    And the response script contains ""$binary_input" /s INSTALLDIR=C:\\temp\\jdk"

  Scenario: Install Oracle JDK on Mysys
    When a hook is requested at /hooks/post/java/8u131-oracle/cygwin
    Then a 200 status code is received
    And a "text/plain; charset=utf-8" content type is received
    And the response script starts with "#!/bin/bash"
    And the response script contains "Post Hook: cygwin-java-msi"
    And the response script contains "A Cygwin post-install hook was found for Java 8u131-oracle"
    And the response script contains ""$binary_input" /s INSTALLDIR=C:\\temp\\jdk"

  Scenario: Install OpenJDK on Cygwin
    When a hook is requested at /hooks/post/java/8u131-zulu/cygwin
    Then a 200 status code is received
    And a "text/plain; charset=utf-8" content type is received
    And the response script starts with "#!/bin/bash"
    And the response script contains "Post Hook: default"
    And the response script contains "No Cygwin post-install hook found for Java 8u131-zulu"
    And the response script contains "mv "$binary_input" "$zip_output""


