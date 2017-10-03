Feature: Install Hooks
	Scenario: A default Post Hook is served
		Given no relevant Hook is available
		When I fetch a "post" hook for "scala" "2.12.0" on "Linux"
		Then a 200 status code is received
		And I receive a hook containing text: Post Hook: default

	Scenario: A Spark Universal Post Hook is served
		When I fetch a "post" hook for "spark" "2.2.0" on "Linux"
		Then a 200 status code is received
		And I receive a hook containing text: Post Hook: default-tarball

	Scenario: A platform specific Post Hook is served
		When I fetch a "post" hook for "java" "8u131" on "Linux"
		Then a 200 status code is received
		And I receive a hook containing text: Post Hook: linux-java-tarball

	Scenario: A platform specific Post Hook is not found
		Given no relevant Hook is available
		When I fetch a "post" hook for "java" "8u111-oracle" on "FreeBSD"
		Then a 404 status code is received

	Scenario: A default Pre Hook is served
		Given no relevant Hook is available
		When I fetch a "pre" hook for "scala" "2.12.0" on "Linux"
		Then a 200 status code is received
		And I receive a hook containing text: Pre Hook: default

	Scenario: An Apple Pre Hook is served
		Given a Hook is available for consumption
		When I fetch a "pre" hook for "java" "6u65-apple" on "Darwin"
		Then a 200 status code is received
		And I receive a hook containing text: Pre Hook: apple-asla

	Scenario: An Oracle Pre Hook is served
		Given a Hook is available for consumption
		When I fetch a "pre" hook for "java" "8u131-oracle" on "Darwin"
		Then a 200 status code is received
		And I receive a hook containing text: Pre Hook: oracle-obcla