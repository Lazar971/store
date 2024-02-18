package com.milosavljevic.lazar.store.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


  @ExceptionHandler
  public ResponseEntity<ErrorResponse> handle(IllegalArgumentException ex) {
    return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
  }

  @ExceptionHandler
  public ResponseEntity<ErrorResponse> handle(Exception ex) {
    return ResponseEntity.internalServerError().body(new ErrorResponse(ex.getMessage()));
  }

  @AllArgsConstructor
  @Getter
  public static class ErrorResponse {
    private String message;
  }
}
