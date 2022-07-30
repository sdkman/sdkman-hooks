package controllers

import domain.Platform
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

  def selfUpdate(beta: Boolean, platformId: String) = Action.async { _ =>
    appRepo.findApplication().map { maybeApp =>
      val response = for {
        stableBaseUrl <- stableBaseUrlO
        betaBaseUrl   <- betaBaseUrlO
        app           <- maybeApp
        stableVersion       = app.stableCliVersion
        betaVersion         = app.betaCliVersion
        stableNativeVersion = app.stableNativeCliVersion
        platform            = Platform(platformId).native
      } yield
        if (beta) {
          Ok(
            views.txt.selfupdate_beta(
              cliVersion = betaVersion,
              cliNativeVersion = stableNativeVersion,
              baseUrl = betaBaseUrl,
              platform = platform,
              beta = true
            )
          )
        } else {
          Ok(
            views.txt.selfupdate_stable(
              cliVersion = stableVersion,
              cliNativeVersion = stableNativeVersion,
              baseUrl = stableBaseUrl,
              platform = platform,
              beta = false
            )
          )
        }
      response getOrElse ServiceUnavailable
    }
  }

  private def configUrl(url: String): Option[String] = config.getOptional[String](url)
}
