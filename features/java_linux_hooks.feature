Feature: Java Linux Hooks

  Scenario Outline:
    When a hook is requested at <uri>
    Then a 200 status code is received
    And the response script contains "<contains>"
    Examples:
      | uri                                          | contains                      |
      | /hooks/post/java/8.0.161-zulu/linuxx64       | Post Hook: linux-java-tarball |
      | /hooks/post/java/8.0.161-zulu/linuxarm64     | Post Hook: linux-java-tarball |
      | /hooks/post/java/8.0.192-zulufx/linuxx64     | Post Hook: linux-java-tarball |
      | /hooks/post/java/8.0.192-zulufx/linuxarm64   | Post Hook: linux-java-tarball |
      | /hooks/post/java/9.0.4-open/linuxx64         | Post Hook: linux-java-tarball |
      | /hooks/post/java/1.0.0-rc5-grl/linuxx64      | Post Hook: linux-java-tarball |
      | /hooks/post/java/8.0.192-amzn/linuxx64       | Post Hook: linux-java-tarball |
      | /hooks/post/java/8.0.202.j9-adpt/linuxx64    | Post Hook: linux-java-tarball |
      | /hooks/post/java/11.0.2-sapmchn/linuxx64     | Post Hook: linux-java-tarball |
      | /hooks/post/java/8.0.202-librca/linuxx64     | Post Hook: linux-java-tarball |
      | /hooks/post/java/11.0.2-sapmchn/linuxarm32sf | Post Hook: linux-java-tarball |
      | /hooks/post/java/8.0.202-librca/linuxarm32hf | Post Hook: linux-java-tarball |
