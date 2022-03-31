package com.szwedo.request.service.system.security;

public enum ApplicationUserPermission {
  CLIENT_READ("client:read"),
  CLIENT_WRITE("client:write"),
  DEVICE_WRITE("device:write"),
  DEVICE_READ("device:read"),
  INVOICE_WRITE("invoice:write"),
  INVOICE_READ("invoice:read"),
  ORDER_WRITE("order:write"),
  ORDER_READ("order:read"),
  USERS_WRITE("users:write"),
  USERS_READ("users:read");

  private final String permission;

  ApplicationUserPermission(String permission) {
    this.permission = permission;
  }

  public String getPermission() {
    return permission;
  }

}
