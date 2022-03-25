package com.szwedo.request.service.system.exception;

public class TechnicianNotFoundException extends RuntimeException {
  public TechnicianNotFoundException(long id) {
    super("The technician with id " + id + " does not found");
  }
}