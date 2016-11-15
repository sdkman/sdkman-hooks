package steps

import cucumber.api.scala.{EN, ScalaDsl}
import org.scalatest.Matchers
import steps.support.World._

import scalaj.http.Http

class HookSteps extends ScalaDsl with EN with Matchers {
  And("""^no relevant Hook is available$""") { () =>
    //do nothing
  }

  And("""^a Hook is available for consumption$""") { () =>
    //do nothing
  }

  And("""^I fetch a hook for "([^"]*)" "([^"]*)" on "([^"]*)"$""") { (candidate: String, version: String, uname: String) =>
    response = Http(s"$host/hooks/post/$candidate/$version/$uname")
      .timeout(connTimeoutMs = 1000, readTimeoutMs = 10000)
      .asString
  }

  And("""^I receive a hook containing text: (.*)$""") { (text: String) =>
    response.body should include(text)
  }
}
