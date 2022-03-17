package com.szwedo.request.service.system.service;

import com.szwedo.request.service.system.dao.UsersRepository;
import com.szwedo.request.service.system.entity.UserEntity;
import com.szwedo.request.service.system.exception.UserNotFoundException;
import com.szwedo.request.service.system.model.UserDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

  private final UsersRepository repository;

  @Override
  public void addUser(UserEntity user) {
    repository.save(user);
  }

  @Override
  public UserDao getUserById(long id) {
    return repository.findById(id)
        .stream()
        .map(user->UserDao.builder()
            .id(user.id())
            .nick(user.nick())
            .password(user.password())
            .status(user.status())
            .build())
        .findFirst()
        .orElseThrow(()->new UserNotFoundException(id));
  }
}
