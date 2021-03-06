package controllers

import java.nio.file.Paths

import com.google.api.services.drive.model.{File => DriveFile}
import javax.inject.Inject
import play.api.cache.AsyncCacheApi
import play.api.mvc.{AbstractController, ControllerComponents}


class ScalaFileUploadController @Inject() (cache: AsyncCacheApi, cc: ControllerComponents) extends AbstractController(cc) {
  def upload = Action(parse.multipartFormData) { request =>
    request.body.file("picture").map { picture =>

      // only get the last part of the filename
      // otherwise someone can send a path like ../../home/foo/bar.txt to write to other files on the system
      val filename = Paths.get(picture.filename).getFileName

      picture.ref.moveTo(Paths.get(s"/tmp/picture/$filename"), replace = true)
      Ok("File uploaded")
    }.getOrElse {
      Redirect(routes.ScalaFileUploadController.upload()).flashing(
        "error" -> "Missing file")
    }
  }

  def uploadFile() {
    val fileType = "image/jpeg"

    def upload(): DriveFile = {
      val file = new DriveFile()
      val result: DriveFile = null
      result
    }
  }
}
