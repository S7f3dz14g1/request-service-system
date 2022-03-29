package com.szwedo.request.service.system.exception;

import java.util.UUID;

public class OrderNotFoundException extends RuntimeException {
  public OrderNotFoundException(UUID id) {
    super("The order with id " + id + " does not found");
  }
}