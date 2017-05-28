Feature: Java OSX Hooks

  Scenario: Install Java 6 on OSX
    When a hook is requested at /hooks/post/java/6u65-apple/darwin
    Then a 200 status code is received
    And a "text/plain; charset=utf-8" content type is recieved
    And the response script starts with "#!/bin/bash"
    And the response script contains "A Mac OSX post-install hook was found for Java 6u65-apple"
    And the response script contains "candidate_work_dir="${work_dir}/java-6u65-apple""
    And the response script contains "mount_volume="/Volumes/Java for OS X 2015-001""
    And the response script contains "jdk_home_folder="/Library/Java/JavaVirtualMachines/1.6.0.jdk/Contents/Home""
    And the response script contains "sudo -E installer -package "${mount_volume}/JavaForOSX.pkg" -target LocalSystem"