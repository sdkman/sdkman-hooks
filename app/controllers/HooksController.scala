package controllers

import play.api.Logger
import play.api.mvc.{Action, _}
import utils.Platform

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class HooksController extends Controller {
  def post(candidate: String, version: String, uname: String) = Action.async { request =>
    Future {
      val platformName = Platform(uname).getOrElse(Platform.Universal).name
      Logger.info(s"Post install hook requested for: $candidate $version $platformName")
      Ok(views.html.default(candidate, version, platformName))
    }
  }
}
