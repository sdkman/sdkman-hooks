package controllers

import javax.inject.Inject
import play.api.Configuration
import play.api.mvc.{AbstractController, ControllerComponents}
import repo.ApplicationRepo

import scala.concurrent.ExecutionContext.Implicits.global

class InstallController @Inject() (
    cc: ControllerComponents,
    appRepo: ApplicationRepo,
    config: Configuration
) extends AbstractController(cc) {

  lazy val fallbackVersion = config.get[String]("service.fallbackVersion")

  lazy val baseUrl = config.get[String]("service.baseUrl")

  def install(rcUpdate: Option[Boolean]) = Action.async { _ =>
    appRepo.findApplication().map { maybeApp =>
      val version = maybeApp.map(_.stableCliVersion).getOrElse(fallbackVersion)
      Ok(views.txt.install(version, baseUrl, rcUpdate.getOrElse(true)))
    }
  }
}
