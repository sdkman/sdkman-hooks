package steps.support

import scalaj.http._

object World {
  val host = "http://localhost:9000"

  var response: HttpResponse[String] = null

  var stableCliVersion: String = null

  var betaCliVersion: String = null
}
