Feature: Install Hooks
	Scenario: A default Post Hook is served
		Given no relevant Hook is available
		When I fetch a "post" hook for "scala" "2.12.0" on "Linux"
		Then a 200 status code is received
		And I receive a hook containing text: Post Hook: default-zip

	Scenario: A Universal Post Hook is served
		When I fetch a "post" hook for "spark" "2.2.0" on "Linux"
		Then a 200 status code is received
		And I receive a hook containing text: Post Hook: default-tarball

	Scenario: A platform specific Post Hook is served
		When I fetch a "post" hook for "java" "9.0.4-open" on "Linux"
		Then a 200 status code is received
		And I receive a hook containing text: Post Hook: linux-java-tarball

	Scenario: A default Pre Hook is served
		Given no relevant Hook is available
		When I fetch a "pre" hook for "scala" "2.12.0" on "Linux"
		Then a 200 status code is received
		And I receive a hook containing text: Pre Hook: default
