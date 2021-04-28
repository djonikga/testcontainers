package ru.djonikga.testcontainers.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.djonikga.testcontainers.error.exception.*;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({AccountNotFoundException.class, UserNotFoundException.class})
  public ResponseEntity<Object> notFoundException(final RuntimeException ex) {
    return buildResponseEntity(new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage()));
  }

  @ExceptionHandler({Exception.class})
  public ResponseEntity<Object> exception(final Exception ex) {
    log.error(ex.getMessage(), ex);
    return buildResponseEntity(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex));
  }

  private ResponseEntity<Object> buildResponseEntity(ErrorResponse responseEntity) {
    return new ResponseEntity<>(responseEntity, responseEntity.getStatus());
  }
}
