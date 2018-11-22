package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents}

/**
  *
  * @param cc
  */
@Singleton
class LoginController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  /**
    * Create an Action to render an HTML page with a welcome message.
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def login = Action {
    Ok(views.html.login());
  }
}
