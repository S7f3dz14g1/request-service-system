package com.szwedo.request.service.system.service;

import com.szwedo.request.service.system.dao.*;
import com.szwedo.request.service.system.entity.*;
import com.szwedo.request.service.system.exception.OrderNotFoundException;
import com.szwedo.request.service.system.model.ClientDao;
import com.szwedo.request.service.system.model.DeviceDto;
import com.szwedo.request.service.system.model.InvoiceDao;
import com.szwedo.request.service.system.model.OrderDetailsDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderDetailsServiceImplTest {

  @Mock
  private UsersRepository userRepository;
  @Mock
  private ClientRepository clientRepository;
  @Mock
  private InvoiceRepository invoiceRepository;
  @Mock
  private DeviceRepository deviceRepository;
  @Mock
  private OrderRepository orderRepository;
  @InjectMocks
  private OrderDetailsServiceImpl orderDetailsService;

  @Test
  public void should_throw_OrderNotFoundException_when_product_does_not_exist() {
    //given
    long orderId = 1L;
    //when
    when(orderRepository.findById(orderId)).thenReturn(Optional.empty());
    //then
    assertThrows(OrderNotFoundException.class, () -> {
      orderDetailsService.getOrderParticular(orderId);
    });
  }

  @Test
  public void should_return_order_details_when_order_exists() {
    //given
    long orderId = 1L;
    OrderDetailsDao expectedResult = OrderDetailsDao.builder()
        .id(orderId)
        .clientDto(ClientDao.builder().id(1L).build())
        .technicianName("nameT")
        .deviceDtoList(List.of(DeviceDto.builder().orderid(orderId).build()))
        .invoiceDao(InvoiceDao.builder().id(1L).build())
        .build();
    //when
    when(orderRepository.findById(orderId)).thenReturn(Optional.of(
        OrderEntity.builder()
            .id(orderId)
            .clientId(1L)
            .technicianId(1L)
            .invoiceId(1L)
            .technicianId(1L)
            .build()
    ));
    when(deviceRepository.getDeviceEntitiesByOrderId(orderId)).thenReturn(
        List.of(DeviceEntity.builder().orderId(orderId).build()));
    when(invoiceRepository.findById(1L)).thenReturn(
        Optional.of(InvoiceEntity.builder().id(1L).build()));
    when(userRepository.findById(1L)).thenReturn(
        Optional.of(UserEntity.builder().id(1L).nick("nameT").build()));
    when(clientRepository.findById(1L)).thenReturn(
        Optional.of(ClientEntity.builder().id(1L).build()));
    //then
    assertEquals(expectedResult, orderDetailsService.getOrderParticular(orderId));
  }
}