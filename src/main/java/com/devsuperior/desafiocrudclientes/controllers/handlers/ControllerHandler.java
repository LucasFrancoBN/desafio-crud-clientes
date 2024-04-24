package com.devsuperior.desafiocrudclientes.controllers.handlers;

import com.devsuperior.desafiocrudclientes.exceptions.CustomError;
import com.devsuperior.desafiocrudclientes.exceptions.ValidationError;
import com.devsuperior.desafiocrudclientes.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerHandler {
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    CustomError error = new CustomError(
        Instant.now(),
        ex.getMessage(),
        request.getRequestURI(),
        status.value()
    );
    return ResponseEntity.status(status).body(error);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ValidationError> methodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
    HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
    ValidationError error = new ValidationError(
        Instant.now(),
        "Invalid data",
        request.getRequestURI(),
        status.value()
    );
    for(FieldError f : ex.getBindingResult().getFieldErrors()) {
      error.addValidationError(f.getField(), f.getDefaultMessage());
    }
    return ResponseEntity.status(status).body(error);
  }
}
