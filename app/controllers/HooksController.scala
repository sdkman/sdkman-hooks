package controllers

import domain.{Candidate, Platform}
import play.api.Logger
import play.api.mvc.{Action, _}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class HooksController extends Controller {
  def post(candidate: String, version: String, uname: String) = Action.async { request =>
    Future {
      val platformName = Platform(uname).getOrElse(Platform.Universal).name
      Logger.info(s"Post install hook requested for: $candidate $version $platformName")

      (candidate, version, Platform(platformName).get) match {
        case (Candidate.Java, "8u111", Platform.Linux) => Ok(views.html.java_8u111_linux())
        case _ => Ok(views.html.default(candidate, version, platformName))
      }
    }
  }
}
