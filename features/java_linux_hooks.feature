Feature: Java Linux Hooks

  Scenario: Install Oracle Java on Linux Post Hook
    When a hook is requested at /hooks/post/java/8u131-oracle/linux
    Then a 200 status code is received
    And a "text/plain; charset=utf-8" content type is received
    And the response script starts with "#!/bin/bash"
    And the response script contains "Post Hook: linux-java-tarball"
    And the response script contains "A Linux post-install hook was found for Java 8u131-oracle"
    And the response script contains "tar zxf "$binary_input" -C "$work_dir""

  Scenario: Install OpenJDK on Linux
    When a hook is requested at /hooks/post/java/8u131-zulu/linux
    Then a 200 status code is received
    And a "text/plain; charset=utf-8" content type is received
    And the response script starts with "#!/bin/bash"
    And the response script contains "Post Hook: linux-java-tarball"
    And the response script contains "A Linux post-install hook was found for Java 8u131-zulu"
    And the response script contains "tar zxf "$binary_input" -C "$work_dir""
