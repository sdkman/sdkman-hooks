package controllers

import domain.Candidate.{Flink, JMC, Java, Spark}
import domain.JdkDistro._
import domain.Platform._
import domain._

import javax.inject.Inject
import play.api.Logging
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class HooksController @Inject() (cc: ControllerComponents)
    extends AbstractController(cc)
    with Logging {

  def hook(
      phase: String,
      candidateId: String,
      version: String,
      platformId: String
  ): Action[AnyContent] =
    Action.async { _ =>
      Future {
        val candidate = Candidate(candidateId)

        val platform = Platform(platformId).getOrElse(Universal)

        val vendor = determineVendor(version)

        logger.info(s"$phase install hook requested for: $candidateId $version ${platform.name}")

        (Hooks.from(phase), candidate, normalise(candidate, version), platform, vendor) match {

          //POST: Mac OSX
          case (Post, Java, _, MacOSX, BellSoft) =>
            Ok(views.txt.default_post_zip(candidate, version, MacOSX))
          case (Post, Java, _, MacOSX, Zulu) =>
            Ok(views.txt.default_post_tarball(candidate, version, MacOSX))
          case (Post, Java, _, MacOSX, ZuluFX) =>
            Ok(views.txt.java_post_osx_tarball(candidate, version, MacOSX))
          case (Post, Java, _, MacOSX, _) =>
            Ok(views.txt.java_post_osx_tarball(candidate, version, MacOSX))
          case (Post, JMC, _, MacOSX, _) =>
            NotFound

          //POST: Linux
          case (Post, Java, _, Linux, _) =>
            Ok(views.txt.java_post_linux_tarball(candidate, version, Linux))
          case (Post, JMC, _, Linux, _) =>
            Ok(
              views.txt.jmc_post_linux_tarball(
                version,
                platform,
                vendor,
                jmcBinaryExec(vendor)
              )
            )

          //POST: Cygwin
          case (Post, Java, "9", Windows64Cygwin, OpenJDK) =>
            Ok(views.txt.default_post_tarball(candidate, version, Windows64Cygwin))
          case (Post, Java, "10", Windows64Cygwin, OpenJDK) =>
            Ok(views.txt.default_post_tarball(candidate, version, Windows64Cygwin))
          case (Post, Java, _, Windows64Cygwin, _) =>
            Ok(views.txt.default_post_zip(candidate, version, Windows64Cygwin))
          case (Post, JMC, _, Windows64Cygwin, _) =>
            NotFound

          //POST: Mysys
          case (Post, Java, "9", Windows64MinGW, OpenJDK) =>
            Ok(views.txt.default_post_tarball(candidate, version, Windows64MinGW))
          case (Post, Java, "10", Windows64MinGW, OpenJDK) =>
            Ok(views.txt.default_post_tarball(candidate, version, Windows64MinGW))
          case (Post, Java, _, Windows64MinGW, _) =>
            Ok(views.txt.default_post_zip(candidate, version, Windows64MinGW))
          case (Post, JMC, _, Windows64MinGW, _) =>
            NotFound

          //POST
          case (Post, Java, _, _, _) =>
            NotFound
          case (Post, Flink, _, _, _) =>
            Ok(views.txt.default_post_tarball(candidate, version, platform))
          case (Post, Spark, _, _, _) =>
            Ok(views.txt.default_post_tarball(candidate, version, platform))
          case (Post, _, _, _, _) =>
            Ok(views.txt.default_post_zip(candidate, version, platform))

          //PRE
          case (Pre, _, _, _, _) =>
            Ok(views.txt.default_pre(candidate, version, platform))

          case (_, _, _, _, _) => NotFound
        }
      }
    }

  private def normalise(candidate: Candidate, version: String): String =
    if (candidate == Java) version.split('.').head else version

  private def determineVendor(version: String) = version.split("-").lastOption.getOrElse("")

  private def jmcBinaryExec(vendor: String) =
    vendor match {
      case "zulu" => "Zulu Mission Control/zmc"
      case _      => "JDK Mission Control/jmc"
    }
}
