Feature: Java OSX Post Hooks

  Scenario: Install Apple Java 6 on OSX
    When a hook is requested at /hooks/post/java/6u65-apple/darwin
    Then a 200 status code is received
    And a "text/plain; charset=utf-8" content type is received
    And the response script starts with "#!/bin/bash"
    And the response script contains "Post Hook: osx-java-6-apple"
    And the response script contains "A Mac OSX post-install hook was found for Java 6u65-apple"
    And the response script contains "candidate_work_dir="${work_dir}/java-6u65-apple""
    And the response script contains "mount_volume="/Volumes/Java for OS X 2015-001""
    And the response script contains "jdk_home_folder="/Library/Java/JavaVirtualMachines/1.6.0.jdk/Contents/Home""
    And the response script contains "sudo -E installer -package "${mount_volume}/JavaForOSX.pkg" -target LocalSystem"

  Scenario: Install Oracle Java 8 on OSX
    When a hook is requested at /hooks/post/java/8u131-oracle/darwin
    Then a 200 status code is received
    And a "text/plain; charset=utf-8" content type is received
    And the response script starts with "#!/bin/bash"
    And the response script contains "Post Hook: osx-java-8-oracle"
    And the response script contains "A Mac OSX post-install hook was found for Java 8u131-oracle"
    And the response script contains "candidate_work_dir="${work_dir}/java-8u131-oracle""
    And the response script contains "mount_volume="/Volumes/JDK 8 Update 131""
    And the response script contains "jdk_home_folder="/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home""
    And the response script contains "sudo -E installer -package "${mount_volume}/JDK 8 Update 131.pkg" -target LocalSystem"

  Scenario: Install Adopt OpenJDK 8 on OSX
    When a hook is requested at /hooks/post/java/8u144-openjdk/darwin
    Then a 200 status code is received
    And a "text/plain; charset=utf-8" content type is received
    And the response script starts with "#!/bin/bash"
    And the response script contains "Post Hook: default-tarball"
    And the response script contains "No Mac OSX post-install hook found for Java 8u144-openjdk"

  Scenario: Install Azul OpenJDK 8 on OSX
    When a hook is requested at /hooks/post/java/8u131-zulu/darwin
    Then a 200 status code is received
    And a "text/plain; charset=utf-8" content type is received
    And the response script starts with "#!/bin/bash"
    And the response script contains "Post Hook: default"
    And the response script contains "No Mac OSX post-install hook found for Java 8u131-zulu"
    And the response script contains "Moving $binary_input to $zip_output"

  Scenario: Install Oracle Java 9 on OSX
    When a hook is requested at /hooks/post/java/9.0.1-oracle/darwin
    Then a 200 status code is received
    And a "text/plain; charset=utf-8" content type is received
    And the response script starts with "#!/bin/bash"
    And the response script contains "Post Hook: osx-java-9-oracle"
    And the response script contains "A Mac OSX post-install hook was found for Java 9.0.1-oracle"
    And the response script contains "candidate_work_dir="${work_dir}/java-9.0.1-oracle""
    And the response script contains "mount_volume="/Volumes/JDK 9.0.1""
    And the response script contains "jdk_home_folder="/Library/Java/JavaVirtualMachines/jdk-9.0.1.jdk/Contents/Home""
    And the response script contains "sudo -E installer -package "${mount_volume}/JDK 9.0.1.pkg" -target LocalSystem"

  Scenario: Install Azul OpenJDK 9 on OSX
    When a hook is requested at /hooks/post/java/9.0.0-zulu/darwin
    Then a 200 status code is received
    And a "text/plain; charset=utf-8" content type is received
    And the response script starts with "#!/bin/bash"
    And the response script contains "Post Hook: default"
    And the response script contains "No Mac OSX post-install hook found for Java 9.0.0-zulu"
    And the response script contains "Moving $binary_input to $zip_output"

  Scenario: Install Adopt OpenJDK 9 on OSX
    When a hook is requested at /hooks/post/java/9u181-openjdk/darwin
    Then a 200 status code is received
    And a "text/plain; charset=utf-8" content type is received
    And the response script starts with "#!/bin/bash"
    And the response script contains "Post Hook: default-tarball"
    And the response script contains "No Mac OSX post-install hook found for Java 9u181-openjdk"