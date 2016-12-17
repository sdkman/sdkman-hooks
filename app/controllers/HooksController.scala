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
      val platform = Platform(uname).getOrElse(Platform.Universal)
      Logger.info(s"$phase install hook requested for: $candidate $version $platform")

      (phase, candidate, version, platform) match {
        case (PostHook, Candidate.Java, "6u65", Platform.MacOSX) =>
          Ok(views.html.java_6u65_osx_post(candidate, version, Platform.MacOSX))
        case (PostHook, Candidate.Java, "8u111", Platform.Linux) =>
          Ok(views.html.java_8u111_linux_post(candidate, version, Platform.Linux))
        case (PostHook, Candidate.Java, "7u79", Platform.MacOSX) =>
          Ok(views.html.java_7u79_osx_post(candidate, version, Platform.MacOSX))
        case (PostHook, Candidate.Java, "8u111", Platform.MacOSX) =>
          Ok(views.html.java_8u111_osx_post(candidate, version, Platform.MacOSX))
        case (PostHook, Candidate.Java, "8u111", Platform.Windows64) =>
          Ok(views.html.java_8u111_cygwin_post(candidate, version, Platform.Windows64))
        case (PostHook, Candidate.Java, _, _) =>
          NotFound
        case (PostHook, _, _, _) =>
          Ok(views.html.default_post(candidate, version, platform.name))
        case (PreHook, Candidate.Java, "6u65", _) =>
          Ok(views.html.default_pre(candidate, version, platform.name))
        case (PreHook, Candidate.Java, "8u111", _) =>
          Ok(views.html.java_pre(candidate, version))
        case (PreHook, _, _, _) =>
          Ok(views.html.default_pre(candidate, version, platform.name))
      }
    }
  }
}
