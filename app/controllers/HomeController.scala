package controllers

import helpers.Auth0Config
import javax.inject._
import play.api.mvc._
import play.api.cache._

import java.security.SecureRandom
import java.math.BigInteger
import java.util.UUID.randomUUID
/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cache: AsyncCacheApi, cc: ControllerComponents) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action {
   Ok(views.html.index("Welcome to Farm Diagnostics."))
  }

  def login = Action {
    val config = Auth0Config.get()
    // Generate random state parameter
    object RandomUtil {
      private val random = new SecureRandom()

      def alphanumeric(nrChars: Int = 24): String = {
        new BigInteger(nrChars * 5, random).toString(32)
      }
    }
    val state = RandomUtil.alphanumeric()

    var audience = config.audience
    if (config.audience == ""){
      audience = String.format("https://%s/userinfo", config.domain)
    }

    val id = randomUUID().toString
    cache.set(id + "state", state)
    val query = String.format(
      "authorize?client_id=%s&redirect_uri=%s&response_type=code&scope=openid profile&audience=%s&state=%s",
      config.clientId,
      config.callbackURL,
      audience,
      state
    )
    Redirect(String.format("https://%s/%s", config.domain, query)).withSession("id" -> id)
  }

  def logout = Action {
    val config = Auth0Config.get()
    Redirect(String.format(
      /**
        * TODO Change returnTo parameter
        */
      "https://%s/v2/logout?client_id=%s&returnTo=https://farmdiagnostics.herokuapp.com",
      config.domain,
      config.clientId)
    ).withNewSession
  }

}
