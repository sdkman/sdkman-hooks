Feature: Java Windows Post Hooks

  Scenario Outline:
    When a hook is requested at <uri>
    Then a 200 status code is received
    And the response script contains "<contains>"
    Examples:
      | uri                                              | contains                   |
      | /hooks/post/java/8.0.152-zulu/cygwin             | Post Hook: default-zip     |
      | /hooks/post/java/8.0.152-zulu/msys_nt-10.0       | Post Hook: default-zip     |
      | /hooks/post/java/8.0.192-zulufx/cygwin           | Post Hook: default-zip     |
      | /hooks/post/java/8.0.192-zulufx/msys_nt-10.0     | Post Hook: default-zip     |
      | /hooks/post/java/9.0.4-open/cygwin               | Post Hook: default-tarball |
      | /hooks/post/java/9.0.4-open/msys_nt-10.0         | Post Hook: default-tarball |
      | /hooks/post/java/10.0.0-open/cygwin              | Post Hook: default-tarball |
      | /hooks/post/java/10.0.0-open/msys_nt-10.0        | Post Hook: default-tarball |
      | /hooks/post/java/11.0.24-open/cygwin             | Post Hook: default-zip     |
      | /hooks/post/java/11.0.24-open/msys_nt-10.0       | Post Hook: default-zip     |
      | /hooks/post/java/12.ea.31-open/cygwin            | Post Hook: default-zip     |
      | /hooks/post/java/12.ea.31-open/msys_nt-10.0      | Post Hook: default-zip     |
      | /hooks/post/java/13.ea.07-open/cygwin            | Post Hook: default-zip     |
      | /hooks/post/java/13.ea.07-open/msys_nt-10.0      | Post Hook: default-zip     |
      | /hooks/post/java/1.0.0-rc5-grl/cygwin            | Post Hook: default-zip     |
      | /hooks/post/java/8.0.192-amzn/cygwin             | Post Hook: default-zip     |
      | /hooks/post/java/8.0.192-amzn/mingw64_nt-10.0    | Post Hook: default-zip     |
      | /hooks/post/java/8.0.202.j9-adpt/cygwin          | Post Hook: default-zip     |
      | /hooks/post/java/8.0.202.j9-adpt/mingw64_nt-10.0 | Post Hook: default-zip     |
      | /hooks/post/java/8.0.202.hs-adpt/cygwin          | Post Hook: default-zip     |
      | /hooks/post/java/8.0.202.hs-adpt/mingw64_nt-10.0 | Post Hook: default-zip     |
      | /hooks/post/java/11.0.2-sapmchn/cygwin           | Post Hook: default-zip     |
      | /hooks/post/java/11.0.2-sapmchn/mingw64_nt-10.0  | Post Hook: default-zip     |
      | /hooks/post/java/8.0.202-librca/cygwin           | Post Hook: default-zip     |
      | /hooks/post/java/8.0.202-librca/mingw64_nt-10.0  | Post Hook: default-zip     |

