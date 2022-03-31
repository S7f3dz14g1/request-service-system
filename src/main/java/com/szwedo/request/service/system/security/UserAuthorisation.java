package com.szwedo.request.service.system.security;

import com.szwedo.request.service.system.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;

@AllArgsConstructor
public class UserAuthorisation implements UserDetails {

  private UserEntity user;

  @Override
  public String getUsername() {
    return user.nick();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return ApplicationUserRole.valueOf(user.status()).getGrantedAuthorities();
  }

  @Override
  public String getPassword() {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    return passwordEncoder.encode(user.password());
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}