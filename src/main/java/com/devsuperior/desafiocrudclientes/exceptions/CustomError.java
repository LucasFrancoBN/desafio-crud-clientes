package com.devsuperior.desafiocrudclientes.exceptions;

import java.time.Instant;

public class CustomError {
  private final Instant timestamp;
  private final String message;
  private final String path;
  private final Integer status;

  public CustomError(Instant timestamp, String message, String path, Integer status) {
    this.timestamp = timestamp;
    this.message = message;
    this.path = path;
    this.status = status;
  }

  public Instant getTimestamp() {
    return timestamp;
  }

  public String getMessage() {
    return message;
  }

  public String getPath() {
    return path;
  }

  public Integer getStatus() {
    return status;
  }
}
