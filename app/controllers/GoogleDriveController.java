package controllers;

import play.api.data.Form;
import play.mvc.*;
import views.html.dashboard;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.media.MediaHttpDownloader;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;


/**
 * This class uses a custom body parser to change the upload type.
 */
@Singleton
public class GoogleDriveController extends Controller {

  private final play.data.FormFactory formFactory;

  @Inject
  public GoogleDriveController(play.data.FormFactory formFactory) {
    this.formFactory = formFactory;
  }


  /**
   * This method uses MyMultipartFormDataBodyParser as the body parser
   */
  @BodyParser.Of(MyMultipartFormDataBodyParser.class)
  public Result upload() throws IOException {
    final Http.MultipartFormData<File> formData = request().body().asMultipartFormData();
    final Http.MultipartFormData.FilePart<File> filePart = formData.getFile("name");
    final File file = filePart.getFile();
    final long data = operateOnTempFile(file);
    return ok("file size = " + data + "");
  }

  private long operateOnTempFile(File file) throws IOException {
    final long size = Files.size(file.toPath());
    Files.deleteIfExists(file.toPath());
    return size;
  }

}
