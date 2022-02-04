package boosti.web.api.file;

public class UnsupportedFileTypeException extends RuntimeException {

  public UnsupportedFileTypeException(String message) {
    super(message);
  }
}
