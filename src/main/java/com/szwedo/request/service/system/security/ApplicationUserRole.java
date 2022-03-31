package com.szwedo.request.service.system.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.szwedo.request.service.system.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {

  TECHNICIAN(Sets.newHashSet(DEVICE_READ,INVOICE_WRITE,INVOICE_READ,ORDER_WRITE,ORDER_READ)),
  ADMIN(Sets.newHashSet(CLIENT_READ,CLIENT_WRITE,DEVICE_WRITE,DEVICE_READ,INVOICE_READ,INVOICE_WRITE,USERS_READ,USERS_WRITE,
      ORDER_READ,ORDER_WRITE));

  private final Set<ApplicationUserPermission> permissions;

  ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
    this.permissions = permissions;
  }

  public Set<ApplicationUserPermission> getPermissions() {
    return permissions;
  }

  public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
    Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
        .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
        .collect(Collectors.toSet());
    permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return permissions;
  }
}