package controllers

import javax.inject._

import play.api.Logger
import play.api.libs.json.Json
import play.api.mvc._
import repo.ApplicationRepo

import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class HealthController @Inject()(appRepo: ApplicationRepo) extends Controller {

  def alive = Action.async { _ =>
    appRepo.isAlive.map { alive =>
      if (alive) {
        val message = statusMessage("OK")
        Logger.info(s"/alive 200 response: $message")
        Ok(message)
      } else NotFound(statusMessage("KO"))
    }.recover {
      case e =>
        val message = errorMessage(e)
        Logger.error(s"/alive 503 response $message")
        ServiceUnavailable(message)
    }
  }

  private def statusMessage(s: String) = Json.obj("status" -> s)

  private def errorMessage(e: Throwable) = Json.obj("status" -> "KO", "error" -> e.getMessage)
}
