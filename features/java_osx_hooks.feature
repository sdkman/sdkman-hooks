Feature: Java OSX Hooks

  Scenario: Install Java 6 on OSX
    When a hook is requested at /hooks/post/java/6u65/darwin
    Then a 200 status code is received
    And a "text/plain; charset=utf-8" content type is recieved
    And the response script starts with "#!/bin/bash"
    And the response script contains "A Mac OSX post-install hook was found for Java 6u65."
    And the response script contains "candidate_work_dir="${work_dir}/java-6u65""
    And the response script contains "mount_volume="/Volumes/Java for OS X 2015-001""
    And the response script contains "jdk_home_folder="/Library/Java/JavaVirtualMachines/1.6.0.jdk/Contents/Home""
    And the response script contains "sudo -E installer -package "${mount_volume}/JavaForOSX.pkg" -target LocalSystem"

  Scenario: Install Java 7 on OSX
    When a hook is requested at /hooks/post/java/7u79/darwin
    Then a 200 status code is received
    And a "text/plain; charset=utf-8" content type is recieved
    And the response script starts with "#!/bin/bash"
    And the response script contains "A Mac OSX post-install hook was found for Java 7u79."
    And the response script contains "candidate_work_dir="${work_dir}/java-7u79""
    And the response script contains "mount_volume="/Volumes/JDK 7 Update 79""
    And the response script contains "jdk_home_folder="/Library/Java/JavaVirtualMachines/jdk1.7.0_79.jdk/Contents/Home""
    And the response script contains "sudo -E installer -package "${mount_volume}/JDK 7 Update 79.pkg" -target LocalSystem"

  Scenario: Install Java 8 series on OSX
    When a hook is requested at /hooks/post/java/8uXYZ/darwin
    Then a 200 status code is received
    And a "text/plain; charset=utf-8" content type is recieved
    And the response script starts with "#!/bin/bash"
    And the response script contains "A Mac OSX post-install hook was found for Java 8uXYZ."
    And the response script contains "candidate_work_dir="${work_dir}/java-8uXYZ""
    And the response script contains "mount_volume="/Volumes/JDK 8 Update XYZ""
    And the response script contains "jdk_home_folder="/Library/Java/JavaVirtualMachines/jdk1.8.0_XYZ.jdk/Contents/Home""
    And the response script contains "sudo -E installer -package "${mount_volume}/JDK 8 Update XYZ.pkg" -target LocalSystem"

  Scenario: Install Java 9 series on OSX
    When a hook is requested at /hooks/post/java/9eaXYZ/darwin
    Then a 200 status code is received
    And a "text/plain; charset=utf-8" content type is recieved
    And the response script starts with "#!/bin/bash"
    And the response script contains "A Mac OSX post-install hook was found for Java 9eaXYZ."
    And the response script contains "candidate_work_dir="${work_dir}/java-8uXYZ""
    And the response script contains "mount_volume="/Volumes/JDK 9""
    And the response script contains "jdk_home_folder="/Library/Java/JavaVirtualMachines/jdk-9.jdk/Contents/Home""
    And the response script contains "sudo -E installer -package "${mount_volume}/JDK 9.pkg" -target LocalSystem"

