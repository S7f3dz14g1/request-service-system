package com.szwedo.request.service.system.service;

import com.szwedo.request.service.system.dao.ClientRepository;
import com.szwedo.request.service.system.entity.ClientEntity;
import com.szwedo.request.service.system.exception.ClientNotFoundException;
import com.szwedo.request.service.system.model.ClientDto;
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
class UserDetailsServiceImplTest {

  @Mock
  private ClientRepository repository;
  @InjectMocks
  private ClientServiceImpl service;

  @Test
  public void should_throw_UserDetailsNotFoundException_when_userDetails_does_not_exist(){
    //given
    long userId=1L;
    //when
    when(repository.findById(userId)).thenReturn(Optional.empty());
    //then
    assertThrows(ClientNotFoundException.class,()->
        service.getUserDetailsById(userId));
  }

  @Test
  public void should_return_userDetails_when_userDetails_exists(){
    //give
    long userId=1L;
    ClientDto expectedResult= ClientDto.builder()
        .id(userId)
        .firstName("imie")
        .lastName("naziwsko")
        .phone(123456789L)
        .email("mail")
        .build();
    ClientEntity entity= ClientEntity.builder()
        .id(userId)
        .firstName("imie")
        .lastName("naziwsko")
        .phone(123456789L)
        .email("mail")
        .build();
    //when
    when(repository.findById(userId)).thenReturn(Optional.of(entity));
    //then
    assertEquals(expectedResult,service.getUserDetailsById(userId));
  }

  @Test
  public void should_add_new_userDetails(){
    //given
    ClientEntity entity= ClientEntity.builder()
        .firstName("imie")
        .lastName("naziwsko")
        .phone(123456789L)
        .email("mail")
        .build();
    ArgumentCaptor<ClientEntity>argument=ArgumentCaptor.forClass(ClientEntity.class);
    //when
    service.addUserDetails(entity);
    //then
    verify(repository).save(argument.capture());
    assertEquals(entity.firstName(),argument.getValue().firstName());
    assertEquals(entity.lastName(),argument.getValue().lastName());
    assertEquals(entity.phone(),argument.getValue().phone());
    assertEquals(entity.email(),argument.getValue().email());
  }

}