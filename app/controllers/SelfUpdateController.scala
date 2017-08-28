package controllers

import javax.inject._

import play.api.Configuration
import play.api.mvc._
import repo.ApplicationRepo

import scala.concurrent.ExecutionContext.Implicits.global

class SelfUpdateController @Inject()(appRepo: ApplicationRepo, config: Configuration) extends Controller {

  def selfUpdate(beta: Boolean) = Action.async { _ =>
    val versionF = if (beta) appRepo.betaCliVersion else appRepo.stableCliVersion
    val channel = if(beta) "beta" else "stable"
    val baseUrl = config.getString("service.baseUrl").getOrElse("invalid")
    versionF.map(v => Ok(views.txt.selfupdate(v, channel, baseUrl)))
  }
}
