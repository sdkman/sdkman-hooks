package steps

import cucumber.api.scala.ScalaDsl
import play.api.Logging
import play.test.Helpers.testServer
import steps.support.World.ServerPort

object Env extends ScalaDsl with Logging {

  val app = testServer(ServerPort)
  app.start()

  sys.addShutdownHook {
    logger.info("Shutting down test server...")
    app.stop()
  }
}
