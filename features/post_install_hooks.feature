Feature: Post Install Hooks
	Scenario: A default Hook is served
		Given no relevant Hook is available
		When I fetch a hook for "scala" "2.12.0" on "Linux"
		Then a 200 status code is received
		And I receive a hook containing text: No Linux post install hook found for scala 2.12.0.
		And I receive a hook containing text: mv "$binary_input" "$zip_output"

	Scenario: A platform specific Hook is served
		Given a Hook is available for consumption
		When I fetch a hook for "java" "8u111" on "LINUX"
		Then a 200 status code is received
		And I receive a hook containing text: A Linux post install hook found for Java 8u111.
		And I receive a hook containing text: tar zxvf "$binary_input" -C "$work_dir"
