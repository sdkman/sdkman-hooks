package controllers

import domain.Platform
import play.api.Configuration
import play.api.mvc.{AbstractController, ControllerComponents}
import repo.ApplicationRepo

import javax.inject.Inject
import scala.concurrent.ExecutionContext.Implicits.global

class InstallController @Inject() (
    cc: ControllerComponents,
    appRepo: ApplicationRepo,
    config: Configuration
) extends AbstractController(cc) {

  private val stableBaseUrlO = configUrl("service.stableBaseUrl")

  private val betaBaseUrlO = configUrl("service.betaBaseUrl")

  def install(beta: Boolean, platformId: String, rcUpdate: Option[Boolean]) = Action.async { _ =>
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
            views.txt.install_beta(
              cliVersion = betaVersion,
              cliNativeVersion = stableNativeVersion,
              baseUrl = betaBaseUrl,
              platform = Platform(platformId).native,
              rcUpdate = rcUpdate.getOrElse(true),
              beta = beta
            )
          )
        } else {
          Ok(
            views.txt.install_stable(
              cliVersion = stableVersion,
              baseUrl = stableBaseUrl,
              rcUpdate = rcUpdate.getOrElse(true),
              beta = beta
            )
          )
        }
      response getOrElse ServiceUnavailable
    }
  }

  private def configUrl(url: String): Option[String] = config.getOptional[String](url)
}
