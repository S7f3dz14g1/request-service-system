package com.szwedo.request.service.system.service;

import com.szwedo.request.service.system.entity.UserEntity;
import com.szwedo.request.service.system.model.UserDao;

public interface UserService {
  void addUser(UserEntity user);

  UserDao getUserById(long id);
}