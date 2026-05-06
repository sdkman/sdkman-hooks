Feature: Relocate Hooks

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