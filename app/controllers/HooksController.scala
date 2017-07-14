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

      (phase, candidate, normalise(version), platform, vendor(version)) match {

        //POST: Mac OSX
        case (PostHook, Candidate.Java, "6u", Platform.MacOSX, "apple") =>
          Ok(views.txt.java_6_apple_osx_post(candidate, dropSuffix(version), Platform.MacOSX))
        case (PostHook, Candidate.Java, "8u", Platform.MacOSX, "oracle") =>
          Ok(views.txt.java_8_oracle_osx_post(candidate, dropSuffix(version), Platform.MacOSX))
        case (PostHook, Candidate.Java, _, Platform.MacOSX, _) =>
          Ok(views.txt.default_post(candidate, version, Platform.MacOSX))

        //POST: Linux
        case (PostHook, Candidate.Java, _, Platform.Linux, _) =>
          Ok(views.txt.java_linux_tarball_post(candidate, version, Platform.Linux))

        //POST: Cygwin
        case (PostHook, Candidate.Java, _, Platform.Windows64, "oracle") =>
          Ok(views.txt.java_cygwin_msi_post(candidate, version, Platform.Windows64))
        case (PostHook, Candidate.Java, _, Platform.Windows64, _) =>
          Ok(views.txt.default_post(candidate, version, Platform.Windows64))

        //POST
        case (PostHook, Candidate.Java, _, _, _) =>
          NotFound
        case (PostHook, _, _, _, _) =>
          Ok(views.txt.default_post(candidate, version, platform))

        //PRE
        case (PreHook, Candidate.Java, "4u19-oracle", Platform.Linux, _) =>
          Ok(views.txt.java_pre_obcla(candidate, version))
        case (PreHook, Candidate.Java, "5u22-oracle", Platform.Linux, _) =>
          Ok(views.txt.java_pre_obcla(candidate, version))
        case (PreHook, Candidate.Java, "5u22-oracle", Platform.Windows64, _) =>
          Ok(views.txt.java_pre_obcla(candidate, version))
        case (PreHook, Candidate.Java, "6u", Platform.MacOSX, "apple") =>
          Ok(views.txt.java_pre_asla(candidate, version))
        case (PreHook, Candidate.Java, "8u", Platform.MacOSX, "oracle") =>
          Ok(views.txt.java_pre_obcla(candidate, version))
        case (PreHook, _, _, _, _) =>
          Ok(views.txt.default_pre(candidate, version, platform))
      }
    }
  }

  implicit class EnhancedJavaVersion(v: String) {
    def isJavaMajor(major: String)(implicit c: Candidate): Boolean = v.startsWith(major) && c == Candidate.Java
  }

  private def normalise(version: String)(implicit c: Candidate) = version match {
    case v if v.isJavaMajor("9") => "9ea"
    case v if v.isJavaMajor("8")=> "8u"
    case v if v.isJavaMajor("6")=> "6u"
    case _ => version
  }

  private def dropSuffix(v: String) = v.split("-").head

  def vendor(version: String) = version.split("-").drop(1).headOption.getOrElse("")
}
