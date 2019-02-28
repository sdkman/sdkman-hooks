package controllers

import domain.{Candidate, Platform, JdkDistro}
import play.api.Logger
import play.api.mvc.{Action, _}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class HooksController extends Controller {

  val PostHook = "post"
  val PreHook = "pre"

  def hook(phase: String, candidateId: String, version: String, platformId: String): Action[AnyContent] =
    Action.async { request =>
      Future {
        implicit val candidate = Candidate(candidateId)

        val platform = Platform(platformId).getOrElse(Platform.Universal)

        Logger.info(s"$phase install hook requested for: $candidateId $version ${platform.name}")

        (phase, candidate, normalise(version), platform, vendor(version)) match {

          //POST: Mac OSX
          case (PostHook, Candidate.Java, "8", Platform.MacOSX, JdkDistro.Oracle) =>
            Ok(views.txt.java_post_8_oracle_osx(candidate, dropSuffix(version), Platform.MacOSX))
          case (PostHook, Candidate.Java, _, Platform.MacOSX, JdkDistro.Zulu) =>
            Ok(views.txt.default_post_tarball(candidate, version, Platform.MacOSX))
          case (PostHook, Candidate.Java, _, Platform.MacOSX, JdkDistro.ZuluFX) =>
            Ok(views.txt.default_post_tarball(candidate, version, Platform.MacOSX))
          case (PostHook, Candidate.Java, _, Platform.MacOSX, _) =>
            Ok(views.txt.java_post_openjdk_osx(candidate, version, Platform.MacOSX))

          //POST: Linux
          case (PostHook, Candidate.Java, _, Platform.Linux, _) =>
            Ok(views.txt.java_post_linux_tarball(candidate, version, Platform.Linux))

          //POST: Cygwin
          case (PostHook, Candidate.Java, _, Platform.Windows64Cygwin, JdkDistro.Oracle) =>
            Ok(views.txt.java_post_cygwin_msi(candidate, version, Platform.Windows64Cygwin))
          case (PostHook, Candidate.Java, "9", Platform.Windows64Cygwin, JdkDistro.OpenJDK) =>
            Ok(views.txt.default_post_tarball(candidate, version, Platform.Windows64Cygwin))
          case (PostHook, Candidate.Java, "10", Platform.Windows64Cygwin, JdkDistro.OpenJDK) =>
            Ok(views.txt.default_post_tarball(candidate, version, Platform.Windows64Cygwin))
          case (PostHook, Candidate.Java, _, Platform.Windows64Cygwin, _) =>
            Ok(views.txt.default_post_zip(candidate, version, Platform.Windows64Cygwin))

          //POST: Mysys
          case (PostHook, Candidate.Java, "9", Platform.Windows64MinGW, JdkDistro.OpenJDK) =>
            Ok(views.txt.default_post_tarball(candidate, version, Platform.Windows64MinGW))
          case (PostHook, Candidate.Java, "10", Platform.Windows64MinGW, JdkDistro.OpenJDK) =>
            Ok(views.txt.default_post_tarball(candidate, version, Platform.Windows64MinGW))
          case (PostHook, Candidate.Java, _, Platform.Windows64MinGW, _) =>
            Ok(views.txt.default_post_zip(candidate, version, Platform.Windows64MinGW))

          //POST
          case (PostHook, Candidate.Java, _, _, _) =>
            NotFound
          case (PostHook, Candidate.Spark, _, _, _) =>
            Ok(views.txt.default_post_tarball(candidate, version, platform))
          case (PostHook, _, _, _, _) =>
            Ok(views.txt.default_post_zip(candidate, version, platform))

          //PRE
          case (PreHook, Candidate.Java, _, Platform.Windows64MinGW, JdkDistro.Oracle) =>
            Ok(views.txt.java_pre_mingw_msi(candidate, version, Platform.Windows64MinGW))
          case (PreHook, Candidate.Java, _, _, JdkDistro.Oracle) =>
            Ok(views.txt.java_pre_obcla(candidate, version))
          case (PreHook, _, _, _, _) =>
            Ok(views.txt.default_pre(candidate, version, platform))
        }
      }
    }

  private def normalise(version: String)(implicit c: Candidate): String =
    if (c == Candidate.Java) version.split('.').head else version

  private def dropSuffix(v: String) = v.split("-").head

  private def vendor(version: String) = version.split("-").lastOption.getOrElse("")
}
