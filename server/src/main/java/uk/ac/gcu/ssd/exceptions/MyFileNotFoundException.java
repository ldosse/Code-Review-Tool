package uk.ac.gcu.ssd.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MyFileNotFoundException extends RuntimeException {
  public MyFileNotFoundException(String s) {
    super(s);
  }
  public MyFileNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
