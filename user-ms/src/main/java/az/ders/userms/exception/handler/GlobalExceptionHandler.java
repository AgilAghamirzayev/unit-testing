package az.ders.userms.exception.handler;

import static az.ders.userms.exception.constants.ExceptionConstants.BAD_REQUEST_EXCEPTION;
import static az.ders.userms.exception.constants.ExceptionConstants.HEADER_MISSING_EXCEPTION;
import static az.ders.userms.exception.constants.ExceptionConstants.METHOD_ARG_TYPE_MISMATCH_EX;
import static az.ders.userms.exception.constants.ExceptionConstants.METHOD_NOT_ALLOWED_EXCEPTION;
import static az.ders.userms.exception.constants.ExceptionConstants.UNEXPECTED_EXCEPTION;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;

import az.ders.userms.exception.AppException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.MethodNotAllowedException;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  @ResponseStatus(INTERNAL_SERVER_ERROR)
  public ExceptionResponse handle(Exception ex, WebRequest request) {
    logError("Exception", ex, request);
    return new ExceptionResponse(UNEXPECTED_EXCEPTION, ex.getMessage());
  }

  @ExceptionHandler(AppException.class)
  public ResponseEntity<ExceptionResponse> handle(AppException ex, WebRequest request) {
    logError("AppException", ex, request);

    ExceptionResponse exceptionResponse = ExceptionResponse.builder()
        .errorMessage(ex.getMessage())
        .userMessage(ex.getUserMessage())
        .build();

    return ResponseEntity.status(ex.getHttpStatus()).body(exceptionResponse);
  }

  @ResponseStatus(METHOD_NOT_ALLOWED)
  @ExceptionHandler(MethodNotAllowedException.class)
  public ExceptionResponse handle(MethodNotAllowedException ex, WebRequest request) {
    logError("MethodNotAllowedException", ex, request);
    return new ExceptionResponse(METHOD_NOT_ALLOWED_EXCEPTION, ex.getMessage());
  }

  @ResponseStatus(BAD_REQUEST)
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ExceptionResponse handleMethodArgumentNotValid(MethodArgumentTypeMismatchException ex,
                                                        WebRequest request) {
    logError("MethodArgumentTypeMismatchException", ex, request);
    return new ExceptionResponse(METHOD_ARG_TYPE_MISMATCH_EX, ex.getMessage());
  }

  @ResponseStatus(BAD_REQUEST)
  @ExceptionHandler(MissingRequestHeaderException.class)
  public ExceptionResponse handleMethodArgumentNotValid(MissingRequestHeaderException ex,
                                                        WebRequest request) {
    logError("MissingRequestHeaderException", ex, request);
    return new ExceptionResponse(HEADER_MISSING_EXCEPTION, ex.getMessage());
  }

  @ResponseStatus(BAD_REQUEST)
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ExceptionResponse handleMethodArgumentNotValid(HttpMessageNotReadableException ex,
                                                        WebRequest request) {
    logError("HttpMessageNotReadableException", ex, request);
    return new ExceptionResponse(BAD_REQUEST_EXCEPTION, ex.getMessage());
  }

  @ResponseStatus(BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ExceptionResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                        WebRequest request) {
    logError("MethodArgumentNotValidException", ex, request);
    List<ValidationErrorResponse> errorsForBadRequest = getErrorsForBadRequest(ex);
    return ExceptionResponse.builder()
        .userMessage("Method arguments not valid")
        .errorMessage(ex.getMessage())
        .validationErrors(errorsForBadRequest)
        .build();
  }

  private List<ValidationErrorResponse> getErrorsForBadRequest(MethodArgumentNotValidException ex) {
    return ex.getBindingResult().getFieldErrors().stream()
        .map(error -> ValidationErrorResponse.builder()
            .field(error.getField())
            .message(error.getDefaultMessage())
            .build())
        .collect(Collectors.toList());
  }

  private void logError(String message, Exception ex, WebRequest request) {
    log.error("[{}] Request: {}, Stack Trace: ", message, request.getDescription(false), ex);
  }

}
