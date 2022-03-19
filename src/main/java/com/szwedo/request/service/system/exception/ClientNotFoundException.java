package com.szwedo.request.service.system.exception;

public class ClientNotFoundException extends RuntimeException {
  public ClientNotFoundException(long id) {
    super("The user details with id " + id + " does not found");
  }
}