package controllers;

import akka.http.impl.util.JavaMapping;
import akka.stream.IOResult;
import akka.stream.Materializer;
import akka.stream.javadsl.FileIO;
import akka.stream.javadsl.Sink;
import akka.util.ByteString;
import play.api.http.HttpErrorHandler;
import play.api.mvc.MultipartFormData;
import play.core.parsers.Multipart;
import play.libs.streams.Accumulator;
import play.mvc.BodyParser;
import play.mvc.Http;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

public class MultipartFormDataWithFileBodyParser extends BodyParser.DelegatingMultipartFormDataBodyParser<File> {

  @Inject
  public MultipartFormDataWithFileBodyParser(Materializer materializer, play.api.http.HttpConfiguration config, HttpErrorHandler errorHandler) {
    super(materializer, config.parser().maxDiskBuffer(), errorHandler);
  }

  /**
   * Generates a temp file directly without going through TemporaryFile.
   */
  private File generateTempFile() {
    try {
      final Path path = Files.createTempFile("multipartBody", "tempFile");
      path.startsWith("farm");
      return path.toFile();
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  @Override
  public Function<Multipart.FileInfo, Accumulator<ByteString, Http.MultipartFormData.FilePart<File>>> createFilePartHandler() {
    return null;
  }
}
