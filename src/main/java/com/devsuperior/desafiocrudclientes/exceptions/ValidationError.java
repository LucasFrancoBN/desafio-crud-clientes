package com.devsuperior.desafiocrudclientes.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends CustomError{
  private final List<FieldMessage> validationErrors = new ArrayList<FieldMessage>();
  public ValidationError(Instant timestamp, String message, String path, Integer status) {
    super(timestamp, message, path, status);
  }

  public List<FieldMessage> getValidationErrors() {
    return validationErrors;
  }

  public void addValidationError(String field, String message) {
    validationErrors.add(new FieldMessage(field, message));
  }
}
