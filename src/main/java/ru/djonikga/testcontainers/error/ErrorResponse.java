package ru.djonikga.testcontainers.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse {

  private HttpStatus status;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  private final LocalDateTime timestamp;
  private String message;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String debugMessage;

  private ErrorResponse() {
    timestamp = LocalDateTime.now();
  }

  public ErrorResponse(HttpStatus status, String message) {
    this();
    this.status = status;
    this.message = message;
  }

  public ErrorResponse(HttpStatus status, String message,
      String debugMessage) {
    this();
    this.status = status;
    this.message = message;
    this.debugMessage = debugMessage;
  }

  public ErrorResponse(HttpStatus status, Throwable ex) {
    this();
    this.status = status;
    this.message = "Unexpected error";
    this.debugMessage = ex.getLocalizedMessage();
  }

  public ErrorResponse(HttpStatus status, String message, Throwable ex) {
    this();
    this.status = status;
    this.message = message;
    this.debugMessage = ex.getLocalizedMessage();
  }
}
