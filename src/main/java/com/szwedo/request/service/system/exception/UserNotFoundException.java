package com.szwedo.request.service.system.exception;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(long id) {
    super("The user with id " + id + " does not found");
  }
}