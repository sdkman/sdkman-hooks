package steps

import cucumber.api.scala.{EN, ScalaDsl}
import org.scalatest.Matchers
import play.api.libs.json.Json
import steps.support.Mongo

import scalaj.http.Http

import steps.support.World._

class HealthSteps extends ScalaDsl with EN with Matchers {

  Before { s =>
    Mongo.insertAliveOk()
  }

  And("""^a request is made to the (.*) endpoint$""") { (endpoint: String) =>
    response = Http(s"$host$endpoint")
      .timeout(connTimeoutMs = 1000, readTimeoutMs = 10000)
      .asString
  }

  And("""^the payload has a "(.*)" of "(.*)"$""") { (key: String, value: String) =>
    val json = Json.parse(response.body)
    val status = (json \ key).as[String]
    status shouldBe value
  }

  After { s =>
    Mongo.dropAppCollection()
  }
}
