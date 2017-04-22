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
      implicit val candidate = Candidate(candidateId)

      val platform = Platform(platformId).getOrElse(Platform.Universal)

      Logger.info(s"$phase install hook requested for: $candidateId $version ${platform.name}")

      (phase, candidate, normalise(version), platform) match {

        //POST: Mac OSX
        case (PostHook, Candidate.Java, "6u65", Platform.MacOSX) =>
          Ok(views.txt.java_6u65_osx_post(candidate, version, Platform.MacOSX))
        case (PostHook, Candidate.Java, "7u79", Platform.MacOSX) =>
          Ok(views.txt.java_7u79_osx_post(candidate, version, Platform.MacOSX))
        case (PostHook, Candidate.Java, "8u", Platform.MacOSX) =>
          Ok(views.txt.java_8uXYZ_osx_post(candidate, version, Platform.MacOSX))
        case (PostHook, Candidate.Java, "9ea", Platform.MacOSX) =>
          Ok(views.txt.java_9eaXYZ_osx_post(candidate, version, Platform.MacOSX))

        //POST: Linux
        case (PostHook, Candidate.Java, "1.4.2_19", Platform.Linux) =>
          Ok(views.txt.java_linux_bin_post(candidate, version, Platform.Linux))
        case (PostHook, Candidate.Java, "5u22", Platform.Linux) =>
          Ok(views.txt.java_linux_bin_post(candidate, version, Platform.Linux))
        case (PostHook, Candidate.Java, "6u45", Platform.Linux) =>
          Ok(views.txt.java_linux_bin_post(candidate, version, Platform.Linux))
        case (PostHook, Candidate.Java, _, Platform.Linux) =>
          Ok(views.txt.java_linux_tarball_post(candidate, version, Platform.Linux))

        //POST: Cygwin
        case (PostHook, Candidate.Java, "5u22", Platform.Windows64) =>
          Ok(views.txt.default_post(candidate, version, Platform.Windows64))
        case (PostHook, Candidate.Java, _, Platform.Windows64) =>
          Ok(views.txt.java_cygwin_post(candidate, version, Platform.Windows64))

        //POST
        case (PostHook, Candidate.Java, _, _) =>
          NotFound
        case (PostHook, _, _, _) =>
          Ok(views.txt.default_post(candidate, version, platform))

        //PRE
        case (PreHook, Candidate.Java, "9ea", _) =>
          Ok(views.txt.java_pre_oeadla(candidate, version))
        case (PreHook, Candidate.Java, _, _) =>
          Ok(views.txt.java_pre_obcla(candidate, version))
        case (PreHook, _, _, _) =>
          Ok(views.txt.default_pre(candidate, version, platform))
      }
    }
  }

  implicit class EnhancedVersion(v: String) {
    def isJavaMajor(major: String)(implicit c: Candidate): Boolean = v.startsWith(major) && c == Candidate.Java
  }

  private def normalise(version: String)(implicit c: Candidate) = version match {
    case v if v.isJavaMajor("9") => "9ea"
    case v if v.isJavaMajor("8")=> "8u"
    case _ => version
  }
}
