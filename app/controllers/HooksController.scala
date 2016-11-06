package controllers

import play.api.mvc.{Action, _}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class HooksController extends Controller {
  def post(candidate: String, version: String, uname: String) = Action.async { request =>
    Future {
      Ok("mv \"$binary_input\" \"$zip_output\"")
    }
  }
}
