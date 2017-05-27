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

      (phase, candidate, version, platform) match {
        //POST
        case (PostHook, Candidate.Java, _, Platform.Linux) =>
          Ok(views.txt.java_linux_tarball_post(candidate, version, Platform.Linux))
        case (PostHook, _, _, _) =>
          Ok(views.txt.default_post(candidate, version, platform))

        //PRE
        case (PreHook, _, _, _) =>
          Ok(views.txt.default_pre(candidate, version, platform))
      }
    }
  }
}
