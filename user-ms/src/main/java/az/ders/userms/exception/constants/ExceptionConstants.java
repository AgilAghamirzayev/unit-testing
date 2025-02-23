package az.ders.userms.exception.constants;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionConstants {

  UNEXPECTED_EXCEPTION("Unexpected exception", INTERNAL_SERVER_ERROR),
  METHOD_NOT_ALLOWED_EXCEPTION("Method not allowed exception", METHOD_NOT_ALLOWED),
  NOT_ANNOTATED("Method must be annotated with @Function", INTERNAL_SERVER_ERROR),
  METHOD_ARGUMENT_NOT_VALID("method argument not valid", BAD_REQUEST),
  NOT_FOUND_EXCEPTION("Not found", NOT_FOUND),
  NOT_EXIST_EXCEPTION("Not exist", BAD_REQUEST),
  HEADER_MISSING_EXCEPTION("Header is missing", BAD_REQUEST),
  METHOD_ARG_TYPE_MISMATCH_EX("Method argument type is not right", BAD_REQUEST),
  BAD_REQUEST_EXCEPTION("Bad request", BAD_REQUEST),
  INVALID_GROUP_ID("Invalid group id", BAD_REQUEST),
  SELECTED_LIMIT_EXCEPTION("Category limit exceed", BAD_REQUEST),
  INVALID_AMOUNT_EXCEPTION("Transfer amount must be positive.", BAD_REQUEST),
  INSUFFICIENT_FUNDS_EXCEPTION("Insufficient balance in account: ", BAD_REQUEST)
  ;

  private final String userMessage;
  private final HttpStatus httpStatus;

}
