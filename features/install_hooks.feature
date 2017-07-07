Feature: Install Hooks
	Scenario: A default Post Hook is served
		Given no relevant Hook is available
		When I fetch a "post" hook for "scala" "2.12.0" on "Linux"
		Then a 200 status code is received
		And I receive a hook containing text: No Linux post-install hook found for Scala 2.12.0.
		And I receive a hook containing text: mv "$binary_input" "$zip_output"

	Scenario: A default Pre Hook is served
		Given no relevant Hook is available
		When I fetch a "pre" hook for "scala" "2.12.0" on "Linux"
		Then a 200 status code is received
		And I receive a hook containing text: No Linux pre-install hook found for Scala 2.12.0.

	Scenario: A platform specific Pre Hook is served
		Given a Hook is available for consumption
		When I fetch a "pre" hook for "java" "6u65-apple" on "Darwin"
		Then a 200 status code is received
		And I receive a hook containing text: A pre-install hook was found for Java 6u65-apple.
		And I receive a hook containing text: Apple requires that you agree with the Apple Software License Agreement

	Scenario: A platform specific Post Hook is not found
		Given no relevant Hook is available
		When I fetch a "post" hook for "java" "8u111-oracle" on "FreeBSD"
		Then a 404 status code is received