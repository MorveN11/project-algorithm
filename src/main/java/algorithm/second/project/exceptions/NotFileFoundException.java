package algorithm.second.project.exceptions;

/**
 * This is the ErrorReadingFileException class.
 */
public class NotFileFoundException extends RuntimeException {

  public NotFileFoundException() {
    super("File not found.");
  }
}
