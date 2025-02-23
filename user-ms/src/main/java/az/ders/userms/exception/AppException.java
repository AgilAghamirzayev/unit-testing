package az.ders.userms.exception;

import az.ders.userms.exception.constants.ExceptionConstants;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AppException extends RuntimeException {

  private final String userMessage;
  private final HttpStatus httpStatus;

  public AppException(String metadata, ExceptionConstants exceptionConstants) {
    super(exceptionConstants.getUserMessage().concat(" " + metadata));
    this.userMessage = exceptionConstants.getUserMessage();
    this.httpStatus = exceptionConstants.getHttpStatus();
  }

}
