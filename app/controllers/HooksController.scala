package controllers

import domain.{Candidate, Platform}
import play.api.Logger
import play.api.mvc.{Action, _}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class HooksController extends Controller {

  val PostHook = "post"
  val PreHook = "pre"

  def hook(phase: String, candidateId: String, version: String, platformId: String) = Action.async { request =>
    Future {
      val candidate = Candidate(candidateId)

      val platform = Platform(platformId).getOrElse(Platform.Universal)

      Logger.info(s"$phase install hook requested for: $candidateId $version ${platform.name}")

      (phase, candidate, version, platform) match {

        //POST: Mac OSX
        case (PostHook, Candidate.Java, "6u65-apple", Platform.MacOSX) =>
          Ok(views.txt.java_6u65_osx_post(candidate, version, Platform.MacOSX))
        case (PostHook, Candidate.Java, _, Platform.MacOSX) =>
          Ok(views.txt.default_post(candidate, version, Platform.MacOSX))

        //POST: Linux
        case (PostHook, Candidate.Java, _, Platform.Linux) =>
          Ok(views.txt.java_linux_tarball_post(candidate, version, Platform.Linux))

        //POST: Cygwin
        case (PostHook, Candidate.Java, _, Platform.Windows64) =>
          Ok(views.txt.default_post(candidate, version, Platform.Windows64))

        //POST
        case (PostHook, Candidate.Java, _, _) =>
          NotFound
        case (PostHook, _, _, _) =>
          Ok(views.txt.default_post(candidate, version, platform))

        //PRE
        case (PreHook, Candidate.Java, "4u19-oracle", Platform.Linux) =>
          Ok(views.txt.java_pre_obcla(candidate, version))
        case (PreHook, Candidate.Java, "5u22-oracle", Platform.Linux) =>
          Ok(views.txt.java_pre_obcla(candidate, version))
        case (PreHook, Candidate.Java, "5u22-oracle", Platform.Windows64) =>
          Ok(views.txt.java_pre_obcla(candidate, version))
        case (PreHook, Candidate.Java, "6u65-apple", Platform.MacOSX) =>
          Ok(views.txt.java_pre_asla(candidate, version))
        case (PreHook, _, _, _) =>
          Ok(views.txt.default_pre(candidate, version, platform))
      }
    }
  }
}
