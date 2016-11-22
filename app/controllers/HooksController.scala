package controllers

import domain.{Candidate, Platform}
import play.api.Logger
import play.api.mvc.{Action, _}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class HooksController extends Controller {

  val PostHook = "post"
  val PreHook = "pre"

  def hook(phase: String, candidate: String, version: String, uname: String) = Action.async { request =>
    Future {
      val platformName = Platform(uname).getOrElse(Platform.Universal).name
      Logger.info(s"$phase install hook requested for: $candidate $version $platformName")

      (phase, candidate, version, platformName) match {
        case (PostHook, Candidate.Java, "8u111", Platform.Linux.name) =>
          Ok(views.html.java_8u111_linux_post(candidate.capitalize, version, Platform.Linux.name))
        case (PostHook, Candidate.Java, "8u111", Platform.MacOSX.name) =>
          Ok(views.html.java_8u111_osx_post(candidate.capitalize, version, Platform.MacOSX.name))
        case (PostHook, Candidate.Java, _, _) =>
          NotFound
        case (PostHook, _, _, _) =>
          Ok(views.html.default_post(candidate.capitalize, version, platformName))
        case (PreHook, Candidate.Java, _, Platform.Linux.name) =>
          Ok(views.html.java_pre(candidate.capitalize, version, Platform.Linux.name))
        case (PreHook, Candidate.Java, _, Platform.MacOSX.name) =>
          Ok(views.html.java_pre(candidate.capitalize, version, Platform.MacOSX.name))
        case (PreHook, Candidate.Java, _, _) =>
          NotFound
        case (PreHook, _, _, _) =>
          Ok(views.html.default_pre(candidate.capitalize, version, platformName))
      }
    }
  }
}
