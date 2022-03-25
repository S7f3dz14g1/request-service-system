package com.szwedo.request.service.system.exception;

public class OrderNotFoundException extends RuntimeException {
  public OrderNotFoundException(long id) {
    super("The order with id " + id + " does not found");
  }
}