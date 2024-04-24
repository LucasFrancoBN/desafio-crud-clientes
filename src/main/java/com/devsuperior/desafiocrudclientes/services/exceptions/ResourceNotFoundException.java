package com.devsuperior.desafiocrudclientes.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{
  public ResourceNotFoundException(String message){
    super(message);
  }
}
