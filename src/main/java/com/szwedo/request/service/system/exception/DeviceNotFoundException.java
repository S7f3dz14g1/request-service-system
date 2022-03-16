package com.szwedo.request.service.system.exception;

public class DeviceNotFoundException extends RuntimeException{
  public DeviceNotFoundException(long id){
    super("The device with id " + id + " does not found");
  }
}
