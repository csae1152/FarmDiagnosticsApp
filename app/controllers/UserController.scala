package controllers

import javax.inject.Inject
import play.api.mvc._
import play.api.mvc.{Action, Controller}
import play.api.libs.json._
import play.api.cache._

class UserController @Inject() (cache: CacheApi) extends Controller {
  def AuthenticatedAction(f: Request[AnyContent] => Result): Action[AnyContent] = {
    Action { request =>
      (request.session.get("id").flatMap { id =>
        cache.get[JsValue](id + "profile")
      } map { profile =>
        f(request)
      }).orElse {
        Some(Redirect(routes.HomeController.index()))
      }.get
    }
  }

  def index = AuthenticatedAction { request =>
    val id = request.session.get("id").get
    val profile = cache.get[JsValue](id + "profile").get
    Ok(views.html.dashboard(profile))
  }
}
