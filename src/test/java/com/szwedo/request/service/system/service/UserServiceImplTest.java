package com.szwedo.request.service.system.service;

import com.szwedo.request.service.system.dao.UsersRepository;
import com.szwedo.request.service.system.entity.UserEntity;
import com.szwedo.request.service.system.exception.UserNotFoundException;
import com.szwedo.request.service.system.model.UserDao;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

  @Mock
  private UsersRepository repository;
  @InjectMocks
  private UserServiceImpl service;

  @Test
  public void should_throw_UserNotFoundException_when_user_does_not_exist() {
    //given
    long userId = 1L;
    //when
    when(repository.findById(userId)).thenReturn(Optional.empty());
    //then
    assertThrows(UserNotFoundException.class, () ->
        service.getUserById(userId));
  }

  @Test
  public void should_return_user_by_id_when_user_exists() {
    //given
    long userId = 1L;
    UserEntity entity = UserEntity
        .builder()
        .nick("nick")
        .password("password")
        .status("status")
        .id(userId)
        .build();
    UserDao expectedResult=UserDao
        .builder()
        .nick("nick")
        .password("password")
        .status("status")
        .id(userId)
        .build();
    //when
    when(repository.findById(userId)).thenReturn(Optional.of(entity));
    //then
    assertEquals(expectedResult,service.getUserById(userId));
  }

  @Test
  public void should_add_new_user(){
    //given
    ArgumentCaptor<UserEntity>argument= ArgumentCaptor.forClass(UserEntity.class);
    UserEntity entity=UserEntity
        .builder()
        .nick("nick")
        .password("password")
        .status("status")
        .build();
    //when
    service.addUser(entity);
    //then
    verify(repository).save(argument.capture());
    assertEquals(entity.nick(),argument.getValue().nick());
    assertEquals(entity.password(),argument.getValue().password());
    assertEquals(entity.status(),argument.getValue().status());
  }
}