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

	Scenario: A platform specific Post Hook is served
		Given a Hook is available for consumption
		When I fetch a "post" hook for "java" "8u111" on "Linux"
		Then a 200 status code is received
		And I receive a hook containing text: A Linux post-install hook was found for Java 8u111.
		And I receive a hook containing text: tar zxf "$binary_input" -C "$work_dir"