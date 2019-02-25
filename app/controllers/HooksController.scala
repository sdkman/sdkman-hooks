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
          case (PostHook, Candidate.Java, _, Platform.MacOSX, JdkDistro.Oracle) =>
            Ok(views.txt.java_post_oracle_osx(candidate, dropSuffix(version), Platform.MacOSX))
          case (PostHook, Candidate.Java, _, Platform.MacOSX, JdkDistro.OpenJDK) =>
            Ok(views.txt.java_post_openjdk_osx(candidate, version, Platform.MacOSX))
          case (PostHook, Candidate.Java, _, Platform.MacOSX, JdkDistro.GraalVM) =>
            Ok(views.txt.java_post_openjdk_osx(candidate, version, Platform.MacOSX))
          case (PostHook, Candidate.Java, _, Platform.MacOSX, JdkDistro.Zulu) =>
            Ok(views.txt.default_post_tarball(candidate, version, Platform.MacOSX))
          case (PostHook, Candidate.Java, _, Platform.MacOSX, JdkDistro.ZuluFX) =>
            Ok(views.txt.default_post_tarball(candidate, version, Platform.MacOSX))
          case (PostHook, Candidate.Java, _, Platform.MacOSX, JdkDistro.Amazon) =>
            Ok(views.txt.java_post_openjdk_osx(candidate, version, Platform.MacOSX))
          case (PostHook, Candidate.Java, _, Platform.MacOSX, JdkDistro.AdoptOpenJDK) =>
            Ok(views.txt.java_post_openjdk_osx(candidate, version, Platform.MacOSX))
          case (PostHook, Candidate.Java, _, Platform.MacOSX, JdkDistro.SapMachine) =>
            Ok(views.txt.java_post_openjdk_osx(candidate, version, Platform.MacOSX))

          //POST: Linux
          case (PostHook, Candidate.Java, _, Platform.Linux, _) =>
            Ok(views.txt.java_post_linux_tarball(candidate, version, Platform.Linux))

          //POST: Cygwin
          case (PostHook, Candidate.Java, _, Platform.Windows64Cygwin, JdkDistro.Oracle) =>
            Ok(views.txt.java_post_cygwin_msi(candidate, version, Platform.Windows64Cygwin))
          case (PostHook, Candidate.Java, _, Platform.Windows64Cygwin, JdkDistro.Zulu) =>
            Ok(views.txt.default_post_zip(candidate, version, Platform.Windows64Cygwin))
          case (PostHook, Candidate.Java, _, Platform.Windows64Cygwin, JdkDistro.ZuluFX) =>
            Ok(views.txt.default_post_zip(candidate, version, Platform.Windows64Cygwin))
          case (PostHook, Candidate.Java, "11", Platform.Windows64Cygwin, JdkDistro.OpenJDK) =>
            Ok(views.txt.default_post_zip(candidate, version, Platform.Windows64Cygwin))
          case (PostHook, Candidate.Java, "12", Platform.Windows64Cygwin, JdkDistro.OpenJDK) =>
            Ok(views.txt.default_post_zip(candidate, version, Platform.Windows64Cygwin))
          case (PostHook, Candidate.Java, _, Platform.Windows64Cygwin, JdkDistro.OpenJDK) =>
            Ok(views.txt.default_post_tarball(candidate, version, Platform.Windows64Cygwin))
          case (PostHook, Candidate.Java, _, Platform.Windows64Cygwin, JdkDistro.Amazon) =>
            Ok(views.txt.default_post_zip(candidate, version, Platform.Windows64Cygwin))
          case (PostHook, Candidate.Java, _, Platform.Windows64Cygwin, JdkDistro.AdoptOpenJDK) =>
            Ok(views.txt.default_post_zip(candidate, version, Platform.Windows64Cygwin))
          case (PostHook, Candidate.Java, _, Platform.Windows64Cygwin, JdkDistro.SapMachine) =>
            Ok(views.txt.default_post_zip(candidate, version, Platform.Windows64Cygwin))

          //POST: Mysys
          case (PostHook, Candidate.Java, _, Platform.Windows64MinGW, JdkDistro.Zulu) =>
            Ok(views.txt.default_post_zip(candidate, version, Platform.Windows64MinGW))
          case (PostHook, Candidate.Java, "11", Platform.Windows64MinGW, JdkDistro.OpenJDK) =>
            Ok(views.txt.default_post_zip(candidate, version, Platform.Windows64MinGW))
          case (PostHook, Candidate.Java, "12", Platform.Windows64MinGW, JdkDistro.OpenJDK) =>
            Ok(views.txt.default_post_zip(candidate, version, Platform.Windows64MinGW))
          case (PostHook, Candidate.Java, _, Platform.Windows64MinGW, JdkDistro.OpenJDK) =>
            Ok(views.txt.default_post_tarball(candidate, version, Platform.Windows64MinGW))
          case (PostHook, Candidate.Java, _, Platform.Windows64MinGW, JdkDistro.AdoptOpenJDK) =>
            Ok(views.txt.default_post_zip(candidate, version, Platform.Windows64MinGW))

          //POST
          case (PostHook, Candidate.Java, _, _, _) =>
            NotFound
          case (PostHook, Candidate.Spark, _, _, _) =>
            Ok(views.txt.default_post_tarball(candidate, version, platform))
          case (PostHook, _, _, _, _) =>
            Ok(views.txt.default_post_zip(candidate, version, platform))

          //PRE
          case (PreHook, Candidate.Java, "8", Platform.Windows64MinGW, JdkDistro.Oracle) =>
            Ok(views.txt.java_pre_mingw_msi(candidate, version, Platform.Windows64MinGW))
          case (PreHook, Candidate.Java, "8", _, JdkDistro.Oracle) =>
            Ok(views.txt.java_pre_obcla(candidate, version))
          case (PreHook, Candidate.Java, "9", _, JdkDistro.Oracle) =>
            Ok(views.txt.java_pre_obcla(candidate, version))
          case (PreHook, Candidate.Java, "10", _, JdkDistro.Oracle) =>
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
    case v if v.isJavaMajor("12") => "12"
    case v if v.isJavaMajor("11") => "11"
    case v if v.isJavaMajor("10") => "10"
    case v if v.isJavaMajor("9") => "9"
    case v if v.isJavaMajor("8") => "8"
    case v if v.isJavaMajor("6") => "6"
    case _ => version
  }

  private def dropSuffix(v: String) = v.split("-").head

  private def vendor(version: String) = version.split("-").lastOption.getOrElse("")
}
