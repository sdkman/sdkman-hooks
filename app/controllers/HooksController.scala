package controllers

import domain.Candidate._
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

        val platform = Platform(platformId)

        val vendor = determineVendor(version)

        logger.info(
          s"$phase install hook requested for: $candidateId $version $platformId as ${platform.name}"
        )

        (Hooks.from(phase), candidate, normalise(candidate, version), platform, vendor) match {

          //POST: Mac OSX
          case (Post, Java, _, MacX64 | MacARM64, BellSoft) =>
            Ok(views.txt.default_post_zip(candidate, version, platform))
          case (Post, Java, _, MacX64 | MacARM64, Zulu) =>
            Ok(views.txt.default_post_tarball(candidate, version, platform))
          case (Post, Java, _, MacX64 | MacARM64, ZuluFX) =>
            Ok(views.txt.java_post_osx_tarball(candidate, version, platform))
          case (Post, Java, _, MacX64 | MacARM64, _) =>
            Ok(views.txt.java_post_osx_tarball(candidate, version, platform))
          case (Post, JMC, _, MacX64 | MacARM64, _) =>
            Ok(views.txt.jmc_post_unix_tarball(version, vendor, jmcBinaryExec(vendor, MacX64)))

          //POST: Linux 64 bit
          case (Post, Java, _, LinuxX32 | LinuxX64 | LinuxARM64 | LinuxARM32HF | LinuxARM32SF, _) =>
            Ok(views.txt.java_post_linux_tarball(candidate, version, platform))
          case (Post, JMC, _, LinuxX32 | LinuxX64 | LinuxARM64 | LinuxARM32HF | LinuxARM32SF, _) =>
            Ok(views.txt.jmc_post_unix_tarball(version, vendor, jmcBinaryExec(vendor, platform)))

          //POST: Windows
          case (Post, Java, "9", Windows64, OpenJDK) =>
            Ok(views.txt.default_post_tarball(candidate, version, platform))
          case (Post, Java, "10", Windows64, OpenJDK) =>
            Ok(views.txt.default_post_tarball(candidate, version, platform))
          case (Post, Java, _, Windows64, _) =>
            Ok(views.txt.default_post_zip(candidate, version, platform))
          case (Post, JMC, _, Windows64, _) =>
            Ok(views.txt.jmc_post_win_zip(version, vendor, jmcBinaryExec(vendor, Windows64)))

          //POST
          case (Post, Java, _, _, _) =>
            NotFound
          case (Post, Flink, _, _, _) =>
            Ok(views.txt.default_post_tarball(candidate, version, platform))
          case (Post, Spark, _, _, _) =>
            Ok(views.txt.default_post_tarball(candidate, version, platform))
          case (Post, Hadoop, _, _, _) =>
            Ok(views.txt.default_post_tarball(candidate, version, platform))
          case (Post, Jextract, _, _, _) =>
            Ok(views.txt.default_post_tarball(candidate, version, platform))
          case (Post, _, _, _, _) =>
            Ok(views.txt.default_post_zip(candidate, version, platform))

          //PRE
          case (Pre, _, _, _, _) =>
            Ok(views.txt.default_pre(candidate, version, platform))

          //RELOCATE
          case (Relocate, Java, _, MacX64 | MacARM64, _) =>
            Ok(views.txt.java_relocate_osx_tarball(candidate, version, platform))
          case (Relocate, JMC, _, MacX64 | MacARM64, _) =>
            Ok(
              views.txt.jmc_relocate_unix_tarball(
                candidate,
                version,
                vendor,
                jmcBinaryExec(vendor, MacX64)
              )
            )
          case (
              Relocate,
              JMC,
              _,
              LinuxX32 | LinuxX64 | LinuxARM64 | LinuxARM32HF | LinuxARM32SF,
              _
              ) =>
            Ok(
              views.txt.jmc_relocate_unix_tarball(
                candidate,
                version,
                vendor,
                jmcBinaryExec(vendor, platform)
              )
            )
          case (Relocate, JMC, _, Windows64, _) =>
            Ok(
              views.txt
                .jmc_relocate_win_zip(candidate, version, vendor, jmcBinaryExec(vendor, Windows64))
            )

          case (_, _, _, _, _) => NotFound
        }
      }
    }

  private def normalise(candidate: Candidate, version: String): String =
    if (candidate == Java) version.split('.').head else version

  private def determineVendor(version: String) = version.split("-").lastOption.getOrElse("")

  private def jmcBinaryExec(vendor: String, platform: Platform) =
    (vendor, platform) match {
      case ("zulu", MacX64 | MacARM64) =>
        "Azul Mission Control.app/Contents/MacOS/zmc"
      case ("zulu", LinuxX32 | LinuxX64 | LinuxARM64 | LinuxARM32SF | LinuxARM32HF) =>
        "Azul Mission Control/zmc"
      case ("zulu", Windows64) =>
        "Azul Mission Control/zmc.exe"
      case (_, LinuxX64) =>
        "JDK Mission Control/jmc"
      case (_, MacX64) =>
        "JDK Mission Control.app/Contents/MacOS/jmc"
      case (_, Windows64) =>
        "JDK Mission Control/jmc.exe"
      case _ => "JDK Mission Control/jmc"
    }
}
