Feature: Install Hooks
	Scenario: A default Post Hook is served
		Given no relevant Hook is available
		When I fetch a "post" hook for "scala" "2.12.0" on "Linux"
		Then a 200 status code is received
		And I receive a hook containing text: No Linux post-install hook found for scala 2.12.0.
		And I receive a hook containing text: mv "$binary_input" "$zip_output"

	Scenario: A default Pre Hook is served
		Given no relevant Hook is available
		When I fetch a "pre" hook for "scala" "2.12.0" on "Linux"
		Then a 200 status code is received
		And I receive a hook containing text: No Linux pre-install hook found for scala 2.12.0.

	Scenario: A platform specific Hook is served
		Given a Hook is available for consumption
		When I fetch a "post" hook for "java" "8u111" on "Linux"
		Then a 200 status code is received
		And I receive a hook containing text: A Linux post install hook found for Java 8u111.
		And I receive a hook containing text: tar zxvf "$binary_input" -C "$work_dir"

	Scenario: A platform specific Hook is not found
		Given no relevant Hook is available
		When I fetch a "post" hook for "java" "8u111" on "FreeBSD"
		Then a 404 status code is received
