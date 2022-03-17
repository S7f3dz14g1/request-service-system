package com.szwedo.request.service.system.controller;

import com.szwedo.request.service.system.entity.UserEntity;
import com.szwedo.request.service.system.model.UserDao;
import com.szwedo.request.service.system.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

  @Mock
  private UserService service;
  @InjectMocks
  private UserController controller;

  @Test
  public void should_add_user() {
    //given
    ArgumentCaptor<UserEntity> argument = ArgumentCaptor.forClass(UserEntity.class);
    UserRequest request = UserRequest.builder()
        .nick("nick")
        .password("password")
        .build();
    //when
    controller.addUser(request);
    //then
    verify(service).addUser(argument.capture());
    assertEquals(request.nick(), argument.getValue().nick());
    assertEquals(request.password(), argument.getValue().password());
  }

  @Test
  public void should_get_user_by_id() {
    //given
    long userId = 1L;
    UserDao userDao = UserDao.builder()
        .id(userId)
        .status("ADMIN")
        .nick("nick")
        .password("password")
        .build();
    //when
    when(service.getUserById(userId)).thenReturn(userDao);
    //then
    assertEquals(userDao, controller.getUserById(userId));
  }
}