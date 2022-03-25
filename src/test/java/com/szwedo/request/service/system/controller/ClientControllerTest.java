package com.szwedo.request.service.system.controller;

import com.szwedo.request.service.system.entity.ClientEntity;
import com.szwedo.request.service.system.model.ClientDao;
import com.szwedo.request.service.system.service.ClientService;
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
class ClientControllerTest {

  @Mock
  private ClientService service;
  @InjectMocks
  private ClientController controller;

  @Test
  public void should_add_userDetails() {
    //given
    ArgumentCaptor<ClientEntity> argumentCaptor = ArgumentCaptor.forClass(ClientEntity.class);
    ClientRequest request = ClientRequest.builder()
        .firstName("imie")
        .lastName("naziwsko")
        .phone(123456789L)
        .email("mail")
        .build();
    ClientEntity expectedResult = ClientEntity.builder()
        .firstname("imie")
        .lastname("naziwsko")
        .phone(123456789L)
        .email("mail")
        .build();
    //when
    controller.addUserDetails(request);
    //then
    verify(service).addUserDetails(argumentCaptor.capture());
    assertEquals(expectedResult.email(), argumentCaptor.getValue().email());
    assertEquals(expectedResult.firstname(), argumentCaptor.getValue().firstname());
    assertEquals(expectedResult.lastname(), argumentCaptor.getValue().lastname());
    assertEquals(expectedResult.phone(), argumentCaptor.getValue().phone());
  }

  @Test
  public void should_get_user_details_by_id() {
    //given
    long userId = 1L;
    ClientDao dto = ClientDao.builder()
        .id(userId)
        .firstName("imie")
        .lastName("naziwsko")
        .phone(123456789L)
        .email("mail")
        .build();
    //when
    when(service.getUserDetailsById(userId)).thenReturn(dto);
    //then
    assertEquals(dto, controller.getUserDetailsById(userId));
  }
}