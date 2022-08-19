Feature: Relocate Hooks

  Scenario: A Relocate Hook request for java OSX served
    When a hook is requested at /hooks/relocate/java/8.0.161-zulu/darwinx64
    Then a 200 status code is received
    And the response script contains "Relocation Hook: osx-java"

  Scenario: A Relocate Hook request for java Linux is not served
    When a hook is requested at /hooks/relocate/java/9.0.4-open/linuxx64
    Then a 404 status code is received

  Scenario Outline:
    When a hook is requested at <uri>
    Then a 200 status code is received
    And the response script contains "<contains>"
    Examples:
      | uri                                            | contains                            |
      | /hooks/relocate/jmc/8.0.0.17-zulu/linuxx64     | Relocation Hook: unix-jmc-tarball   |
      | /hooks/relocate/jmc/8.0.0-adpt/linuxx64        | Relocation Hook: unix-jmc-tarball   |
      | /hooks/relocate/jmc/8.0.0.17-zulu/darwinx64    | Relocation Hook: unix-jmc-tarball   |
      | /hooks/relocate/jmc/8.0.0-adpt/darwinx64       | Relocation Hook: unix-jmc-tarball   |
      | /hooks/relocate/jmc/8.0.0-adpt/cygwin          | Relocation Hook: win-jmc-zip        |
      | /hooks/relocate/jmc/8.0.0-adpt/msys_nt-10.0    | Relocation Hook: win-jmc-zip        |