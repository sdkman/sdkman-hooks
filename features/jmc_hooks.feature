Feature: Java Mission Control hooks

  Scenario Outline:
    When a hook is requested at <uri>
    Then a 200 status code is received
    And the response script contains "<contains>"
    Examples:
      | uri                                        | contains                      |
      | /hooks/post/jmc/8.0.0.17-zulu/linuxx64     | Post Hook: unix-jmc-tarball   |
      | /hooks/post/jmc/8.0.0-adpt/linuxx64        | Post Hook: unix-jmc-tarball   |
      | /hooks/post/jmc/8.0.0.17-zulu/darwinx64    | Post Hook: unix-jmc-tarball   |
      | /hooks/post/jmc/8.0.0-adpt/darwinx64       | Post Hook: unix-jmc-tarball   |
      | /hooks/post/jmc/8.0.0-adpt/cygwin          | Post Hook: win-jmc-zip        |
      | /hooks/post/jmc/8.0.0-adpt/msys_nt-10.0    | Post Hook: win-jmc-zip        |
