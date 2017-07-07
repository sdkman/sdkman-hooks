package steps

import cucumber.api.PendingException
import cucumber.api.scala.{EN, ScalaDsl}
import org.scalatest.{Matchers, OptionValues}
import steps.support.World._

import scalaj.http.Http

class HookSteps extends ScalaDsl with EN with Matchers with OptionValues {
  And("""^no relevant Hook is available$""") { () =>
    //do nothing
  }

  And("""^a Hook is available for consumption$""") { () =>
    //do nothing
  }

  And("""^I fetch a "(.*)" hook for "(.*)" "(.*)" on "(.*)"$""") { (phase: String, candidate: String, version: String, uname: String) =>
    response = Http(s"$host/hooks/$phase/$candidate/$version/$uname")
      .timeout(connTimeoutMs = 1000, readTimeoutMs = 10000)
      .asString
  }

  And("""^I receive a hook containing text: (.*)$""") { (text: String) =>
    response.body should include(text)
  }

  And("""^a hook is requested at (.*)$""") { (uri: String) =>
    response = Http(s"$host$uri")
      .timeout(connTimeoutMs = 1000, readTimeoutMs = 10000)
      .asString
  }

  And("""^a (\d+) status code is received$""") { (status: Int) =>
    response.code shouldBe status
  }

  And("""^a "(.*)" content type is received$""") { (contentType: String) =>
    response.contentType.value shouldBe contentType
  }

  And("""^the response script starts with "(.*)"$""") { (content: String) =>
    response.body should startWith(content)
  }

  And("""^the response script contains "(.*)"$""") { (content: String) =>
    response.body should include(content)
  }

  And("""^the response script does not contain "(.*)"$""") { (content: String) =>
    response.body should not include content
  }
}
