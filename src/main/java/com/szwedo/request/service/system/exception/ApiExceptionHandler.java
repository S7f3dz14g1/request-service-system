package com.szwedo.request.service.system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(value = {DeviceNotFoundException.class})
  public ResponseEntity<Object> handleDeviceNotFoundException(DeviceNotFoundException e){
    HttpStatus status=HttpStatus.NOT_FOUND;
    ApiException apiException=getApiException(status, e.getMessage());
    return new ResponseEntity<>(apiException,status);
  }

  @ExceptionHandler(value = { MethodArgumentNotValidException.class })
  ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    ApiException apiException = getApiException(status,"Invalid Json fields.");
    return new ResponseEntity<>(apiException, status);
  }

  private ApiException getApiException(HttpStatus status, String message) {
    return ApiException.builder()
        .message(message)
        .status(status)
        .timeZone(ZonedDateTime.now(ZoneId.of("Z")))
        .build();
  }
}
