package steps

import cucumber.api.scala.{EN, ScalaDsl}
import org.scalatest.Matchers
import play.api.libs.json.Json
import steps.support.Mongo

import scalaj.http.Http

import steps.support.World._

class Steps extends ScalaDsl with EN with Matchers {

  And("The database is alive") { () =>
    Mongo.insertAliveOk()
  }

  And("""^the stable CLI Version is "(.*)"""") { stable: String =>
    stableCliVersion = stable
  }

  And("""^the beta CLI Version is "(.*)"""") { beta: String =>
    betaCliVersion = beta
  }

  And("""^the stable native CLI Version is "(.*)"""") { native: String =>
    stableNativeCliVersion = native
  }
  
  And("""^the beta native CLI Version is "(.*)"""") { native: String =>
    betaNativeCliVersion = native
    // Insert all CLI versions after both stable and beta native CLI versions are set
    Mongo.insertCliVersions(stableCliVersion, betaCliVersion, stableNativeCliVersion, betaNativeCliVersion)
  }

  And("""^a request is made to the (.*) endpoint$""") { endpoint: String =>
    response = Http(s"$host$endpoint")
      .timeout(connTimeoutMs = 1000, readTimeoutMs = 10000)
      .asString
  }

  And("""^the payload has a "(.*)" of "(.*)"$""") { (key: String, value: String) =>
    val json   = Json.parse(response.body)
    val status = (json \ key).as[String]
    status shouldBe value
  }

  Before { s =>
    Mongo.dropAppCollection()
  }
}
