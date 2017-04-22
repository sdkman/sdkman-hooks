Feature: Java Cygwin Hooks

  Scenario: Install Java on Cygwin
    When a hook is requested at /hooks/post/java/8u131/cygwin64
    Then a 200 status code is received
    And a "text/plain; charset=utf-8" content type is recieved
    And the response script starts with "#!/bin/bash"
    And the response script contains "A Cygwin post-install hook was found for Java 8u131."
    And the response script contains ""$binary_input" /s INSTALLDIR=C:\\temp\\jdk"
