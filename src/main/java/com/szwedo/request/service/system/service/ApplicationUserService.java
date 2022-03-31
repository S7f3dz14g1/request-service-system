package com.szwedo.request.service.system.service;

import com.szwedo.request.service.system.dao.UsersRepository;
import com.szwedo.request.service.system.entity.UserEntity;
import com.szwedo.request.service.system.security.UserAuthorisation;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ApplicationUserService implements UserDetailsService {

  private final UsersRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<UserEntity> user = userRepository.findByName(username);
    if (user.isEmpty())
      throw new UsernameNotFoundException(String.format("Could not find %s user", username));
    return new UserAuthorisation(user.get());
  }
}