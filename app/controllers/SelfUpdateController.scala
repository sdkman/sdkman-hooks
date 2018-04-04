package controllers

import javax.inject._
import play.api.Configuration
import play.api.mvc._
import repo.ApplicationRepo

import scala.concurrent.ExecutionContext.Implicits.global

class SelfUpdateController @Inject()(config: Configuration, appRepo: ApplicationRepo) extends Controller {

  lazy val fallbackVersion = config.getString("service.fallbackVersion").getOrElse("NA")

  def selfUpdate(beta: Boolean) = Action.async { _ =>
    val baseUrl = config.getString("service.baseUrl").getOrElse("invalid")
    appRepo.findApplication().map { maybeApp =>
      val (channel, version) = if (beta) ("beta", maybeApp.map(_.betaCliVersion)) else ("stable", maybeApp.map(_.stableCliVersion))
      Ok(views.txt.selfupdate(version.getOrElse(fallbackVersion), channel, baseUrl))
    }
  }
}
