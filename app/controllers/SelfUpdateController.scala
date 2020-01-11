package controllers

import javax.inject._
import play.api.Configuration
import play.api.mvc._
import repo.ApplicationRepo

import scala.concurrent.ExecutionContext.Implicits.global

class SelfUpdateController @Inject()(cc: ControllerComponents, config: Configuration, appRepo: ApplicationRepo) extends AbstractController(cc) {

  lazy val fallbackVersion = config.get[String]("service.fallbackVersion")

  def selfUpdate(beta: Boolean) = Action.async { _ =>
    val baseUrl = config.get[String]("service.baseUrl")
    appRepo.findApplication().map { maybeApp =>
      val (channel, version) = if (beta) ("beta", maybeApp.map(_.betaCliVersion)) else ("stable", maybeApp.map(_.stableCliVersion))
      Ok(views.txt.selfupdate(version.getOrElse(fallbackVersion), channel, baseUrl))
    }
  }
}
