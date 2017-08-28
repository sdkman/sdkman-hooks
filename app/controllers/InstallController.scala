package controllers

import javax.inject.Inject

import play.api.Configuration
import play.api.mvc.{Action, Controller}
import repo.ApplicationRepo

import scala.concurrent.ExecutionContext.Implicits.global

class InstallController @Inject() (appRepo: ApplicationRepo, config: Configuration) extends Controller {

  def install = Action.async { _ =>
    val baseUrl = config.getString("service.baseUrl").getOrElse("invalid")
    appRepo.stableCliVersion.map { version =>
      Ok(views.txt.install(version, baseUrl))
    }
  }
}
