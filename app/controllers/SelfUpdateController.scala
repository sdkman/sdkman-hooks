package controllers

import play.api.Configuration
import play.api.mvc._
import repo.ApplicationRepo

import javax.inject._
import scala.concurrent.ExecutionContext.Implicits.global

class SelfUpdateController @Inject() (
    cc: ControllerComponents,
    config: Configuration,
    appRepo: ApplicationRepo
) extends AbstractController(cc) {

  private val stableBaseUrlO = configUrl("service.stableBaseUrl")

  private val betaBaseUrlO = configUrl("service.betaBaseUrl")

  def selfUpdate(beta: Boolean) = Action.async { _ =>
    appRepo.findApplication().map { maybeApp =>
      val response = for {
        stableBaseUrl <- stableBaseUrlO
        betaBaseUrl   <- betaBaseUrlO
        app           <- maybeApp
        stableVersion       = app.stableCliVersion
        betaVersion         = app.betaCliVersion
        stableNativeVersion = app.stableNativeCliVersion
      } yield
        if (beta) {
          Ok(
            views.txt.selfupdate_beta(
              cliVersion = betaVersion,
              cliNativeVersion = stableNativeVersion,
              baseUrl = betaBaseUrl
            )
          )
        } else {
          Ok(
            views.txt.selfupdate_stable(
              cliVersion = stableVersion,
              baseUrl = stableBaseUrl
            )
          )
        }
      response getOrElse ServiceUnavailable
    }
  }

  private def configUrl(url: String): Option[String] = config.getOptional[String](url)
}
