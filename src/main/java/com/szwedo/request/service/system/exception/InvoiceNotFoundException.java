package com.szwedo.request.service.system.exception;

public class InvoiceNotFoundException extends RuntimeException {
  public InvoiceNotFoundException(long id) {
    super("The device with id " + id + " does not found");
  }
}