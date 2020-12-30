Feature: Java OSX Hooks

  Scenario Outline:
    When a hook is requested at <uri>
    Then a 200 status code is received
    And the response script contains "<contains>"
    Examples:
      | uri                                        | contains                    |
      | /hooks/pre/java/8.0.161-zulu/darwinx64     | Pre Hook: default           |
      | /hooks/post/java/8.0.161-zulu/darwinx64    | Post Hook: default-tarball  |
      | /hooks/post/java/9.0.4-zulu/darwinx64      | Post Hook: default-tarball  |
      | /hooks/post/java/10.0.0-zulu/darwinx64     | Post Hook: default-tarball  |
      | /hooks/post/java/11.0.2-zulu/darwinx64     | Post Hook: default-tarball  |
      | /hooks/post/java/11.0.2-zulu/darwinarm64   | Post Hook: default-tarball  |
      | /hooks/post/java/8.0.202-zulufx/darwinx64  | Post Hook: osx-java-tarball |
      | /hooks/post/java/11.0.2-zulufx/darwinx64   | Post Hook: osx-java-tarball |
      | /hooks/post/java/9.0.4-open/darwinx64      | Post Hook: osx-java-tarball |
      | /hooks/post/java/10.0.0-open/darwinx64     | Post Hook: osx-java-tarball |
      | /hooks/post/java/11.0.2-open/darwinx64     | Post Hook: osx-java-tarball |
      | /hooks/post/java/1.0.0-rc5-grl/darwinx64   | Post Hook: osx-java-tarball |
      | /hooks/post/java/8.0.192-amzn/darwinx64    | Post Hook: osx-java-tarball |
      | /hooks/post/java/8.0.202.j9-adpt/darwinx64 | Post Hook: osx-java-tarball |
      | /hooks/post/java/11.0.2-sapmchn/darwinx64  | Post Hook: osx-java-tarball |
      | /hooks/post/java/8.0.202-librca/darwinx64  | Post Hook: default-zip      |
