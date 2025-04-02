package controllers

import domain.Platform
import play.api.Configuration
import play.api.mvc.{AbstractController, ControllerComponents}
import repo.ApplicationRepo

import javax.inject.Inject
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class InstallController @Inject() (
    cc: ControllerComponents,
    appRepo: ApplicationRepo,
    config: Configuration
) extends AbstractController(cc) {

  private val stableBaseUrlO = configUrl("service.stableBaseUrl")

  private val betaBaseUrlO = configUrl("service.betaBaseUrl")

  def install(beta: Boolean, rcUpdate: Option[Boolean], ci: Option[Boolean]) = Action.async { _ =>
    appRepo.findApplication().map { maybeApp =>
      val response = for {
        stableBaseUrl <- stableBaseUrlO
        betaBaseUrl   <- betaBaseUrlO
        app           <- maybeApp
        stableVersion       = app.stableCliVersion
        betaVersion         = app.betaCliVersion
        stableNativeVersion = app.stableNativeCliVersion
        betaNativeVersion   = app.betaNativeCliVersion
      } yield
        if (beta) {
          Ok(
            views.txt.install_beta(
              cliVersion = betaVersion,
              cliNativeVersion = betaNativeVersion,
              baseUrl = betaBaseUrl,
              rcUpdate = rcUpdate.getOrElse(true),
              beta = true,
              ci = ci.getOrElse(false)
            )
          )
        } else {
          Ok(
            views.txt.install_stable(
              cliVersion = stableVersion,
              cliNativeVersion = stableNativeVersion,
              baseUrl = stableBaseUrl,
              rcUpdate = rcUpdate.getOrElse(true),
              beta = false,
              ci = ci.getOrElse(false)
            )
          )
        }
      response getOrElse ServiceUnavailable
    }
  }

  private def configUrl(url: String): Option[String] = config.getOptional[String](url)
}
