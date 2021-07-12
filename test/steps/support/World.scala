package steps.support

import scalaj.http._

object World {

  val ServerPort = 9001

  val host = s"http://localhost:${ServerPort}"

  var response: HttpResponse[String] = null

  var stableCliVersion = ""

  var betaCliVersion = ""
}
