package controllers

import javax.inject.Inject
import play.api.Configuration
import play.api.mvc.{Action, Controller}
import repo.ApplicationRepo

import scala.concurrent.ExecutionContext.Implicits.global

class InstallController @Inject() (appRepo: ApplicationRepo, config: Configuration) extends Controller {

  lazy val fallbackVersion = config.getString("service.fallbackVersion").getOrElse("invalid")

  lazy val baseUrl = config.getString("service.baseUrl").getOrElse("invalid")

  def install = Action.async { _ =>
    appRepo.findApplication().map { maybeApp =>
      val version = maybeApp.map(_.stableCliVersion).getOrElse(fallbackVersion)
      Ok(views.txt.install(version, baseUrl))
    }
  }
}
