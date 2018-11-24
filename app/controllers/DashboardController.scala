package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents}

@Singleton
class DashboardController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def dashboard = Action {
    Ok(views.html.dashboard());
  }
}
