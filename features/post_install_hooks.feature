Feature: Post Install Hooks
	Scenario: A default Hook is served
		Given no relevant Hook is available
		When I fetch a hook for "scala" "2.12.0" on "UNIVERSAL"
		Then a 200 status code is received
		And I receive a hook containing text: mv "$binary_input" "$zip_output"
