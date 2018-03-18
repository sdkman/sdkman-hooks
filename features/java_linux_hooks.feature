Feature: Java Linux Hooks

  Scenario: Install Oracle Java 8 on Linux Pre Hook
    When a hook is requested at /hooks/pre/java/8u131-oracle/linux
    Then a 200 status code is received
    And a "text/plain; charset=utf-8" content type is received
    And the response script contains "Pre Hook: oracle-obcla"
    And the response script starts with "#!/bin/bash"
    And the response script contains "A pre-install hook was found for Java 8u131-oracle."
    And the response script contains "Oracle requires that you agree with the Oracle Binary Code License Agreement"

  Scenario: Install Oracle Java 8 on Linux Post Hook
    When a hook is requested at /hooks/post/java/8u131-oracle/linux
    Then a 200 status code is received
    And a "text/plain; charset=utf-8" content type is received
    And the response script starts with "#!/bin/bash"
    And the response script contains "Post Hook: linux-java-tarball"
    And the response script contains "A Linux post-install hook was found for Java 8u131-oracle"
    And the response script contains "tar zxf "$binary_input" -C "$work_dir""

  Scenario: Install Oracle Java 9 on Linux Pre Hook
    When a hook is requested at /hooks/pre/java/9_181-oracle/linux
    Then a 200 status code is received
    And a "text/plain; charset=utf-8" content type is received
    And the response script contains "Pre Hook: oracle-obcla"
    And the response script starts with "#!/bin/bash"
    And the response script contains "A pre-install hook was found for Java 9_181-oracle."
    And the response script contains "Oracle requires that you agree with the Oracle Binary Code License Agreement"

  Scenario: Install Oracle Java 9 on Linux Post Hook
    When a hook is requested at /hooks/post/java/9_181-oracle/linux
    Then a 200 status code is received
    And a "text/plain; charset=utf-8" content type is received
    And the response script starts with "#!/bin/bash"
    And the response script contains "Post Hook: linux-java-tarball"
    And the response script contains "A Linux post-install hook was found for Java 9_181-oracle"
    And the response script contains "tar zxf "$binary_input" -C "$work_dir""

  Scenario: Install Azul OpenJDK on Linux
    When a hook is requested at /hooks/post/java/8u131-zulu/linux
    Then a 200 status code is received
    And a "text/plain; charset=utf-8" content type is received
    And the response script starts with "#!/bin/bash"
    And the response script contains "Post Hook: linux-java-tarball"
    And the response script contains "A Linux post-install hook was found for Java 8u131-zulu"
    And the response script contains "tar zxf "$binary_input" -C "$work_dir""

  Scenario: Install Adopt OpenJDK on Linux
    When a hook is requested at /hooks/post/java/8u144-openjdk/linux
    Then a 200 status code is received
    And a "text/plain; charset=utf-8" content type is received
    And the response script starts with "#!/bin/bash"
    And the response script contains "Post Hook: linux-java-tarball"
    And the response script contains "A Linux post-install hook was found for Java 8u144-openjdk"
    And the response script contains "tar zxf "$binary_input" -C "$work_dir""
