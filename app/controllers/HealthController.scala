package controllers

import javax.inject._
import play.api.Logging
import play.api.libs.json.Json
import play.api.mvc._
import repo.ApplicationRepo

import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class HealthController @Inject()(cc: ControllerComponents, appRepo: ApplicationRepo) extends AbstractController(cc) with Logging {

  def alive = Action.async { request =>
    appRepo.findApplication().map { maybeApp =>
      maybeApp.fold(NotFound(statusMessage("KO"))) { app =>
        val message = statusMessage(app.alive)
        logger.info(s"/alive 200 response: $message")
        Ok(message)
      }
    }.recover {
      case e =>
        val message = errorMessage(e)
        logger.error(s"/alive 503 response $message")
        ServiceUnavailable(message)
    }
  }

  private def statusMessage(s: String) = Json.obj("status" -> s)

  private def errorMessage(e: Throwable) = Json.obj("status" -> "KO", "error" -> e.getMessage)
}
