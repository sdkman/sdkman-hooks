Feature: Java Linux Hooks

  Scenario: Install Java 1.4.2_19 on Linux 64
    When a hook is requested at /hooks/post/java/1.4.2_19/linux64
    Then a 200 status code is received
    And a "text/plain; charset=utf-8" content type is recieved
    And the response script starts with "#!/bin/bash"
    And the response script contains "A Linux post-install hook was found for Java 1.4.2_19."
    And the response script does not contain "/usr/bin/env tar zxf "$binary_input" -C "$work_dir""
    And the response script contains "/usr/bin/env zip -qr "$zip_output" ."

  Scenario: Install Java 5u22 on Linux 64
    When a hook is requested at /hooks/post/java/5u22/linux64
    Then a 200 status code is received
    And a "text/plain; charset=utf-8" content type is recieved
    And the response script starts with "#!/bin/bash"
    And the response script contains "A Linux post-install hook was found for Java 5u22."
    And the response script does not contain "/usr/bin/env tar zxf "$binary_input" -C "$work_dir""
    And the response script contains "/usr/bin/env zip -qr "$zip_output" ."

  Scenario: Install Java 6u45 on Linux 64
    When a hook is requested at /hooks/post/java/6u45/linux64
    Then a 200 status code is received
    And a "text/plain; charset=utf-8" content type is recieved
    And the response script starts with "#!/bin/bash"
    And the response script contains "A Linux post-install hook was found for Java 6u45."
    And the response script does not contain "/usr/bin/env tar zxf "$binary_input" -C "$work_dir""
    And the response script contains "/usr/bin/env zip -qr "$zip_output" ."

  Scenario: Install Java 7 and above on Linux 64
    When a hook is requested at /hooks/post/java/8u131/linux64
    Then a 200 status code is received
    And a "text/plain; charset=utf-8" content type is recieved
    And the response script starts with "#!/bin/bash"
    And the response script contains "A Linux post-install hook was found for Java 8u131."
    And the response script contains "/usr/bin/env tar zxf "$binary_input" -C "$work_dir""
