package com.szwedo.request.service.system.service;

import com.szwedo.request.service.system.controller.OrderRequest;
import com.szwedo.request.service.system.dao.*;
import com.szwedo.request.service.system.entity.*;
import com.szwedo.request.service.system.exception.OrderNotFoundException;
import com.szwedo.request.service.system.exception.TechnicianNotFoundException;
import com.szwedo.request.service.system.model.DeviceDto;
import com.szwedo.request.service.system.model.OrderDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

  @Mock
  private UsersRepository usersRepository;
  @Mock
  private OrderRepository orderRepository;
  @Mock
  private ClientRepository clientRepository;
  @Mock
  private DeviceRepository deviceRepository;
  @Mock
  private InvoiceRepository invoiceRepository;
  @InjectMocks
  private OrderServiceImpl service;

  @Test
  public void should_throw_OrderNotFoundException_when_order_does_not_exist() {
    //given
    UUID orderId = UUID.randomUUID();
    Long invoiceId = 1L;
    Long technicianId = 1L;
    String status = "status";
    //when
    when(orderRepository.findById(orderId)).thenReturn(Optional.empty());
    //then
    assertThrows(OrderNotFoundException.class, () -> {
      service.changeStatus(orderId, status);
    });
    assertThrows(OrderNotFoundException.class, () -> {
      service.setInvoiceId(orderId, invoiceId);
    });
    assertThrows(OrderNotFoundException.class, () -> {
      service.setTechnician(orderId, technicianId);
    });
    assertThrows(OrderNotFoundException.class, () -> {
      service.getOrderById(orderId);
    });
  }

  @Test
  public void should_throw_TechnicianNotFoundException_when_technician_does_not_exist() {
    //given
    UUID orderId = UUID.randomUUID();
    Long technicianId = 1L;
    //when
    when(orderRepository.findById(orderId)).thenReturn(Optional.of(
        OrderEntity.builder().build()
    ));
    when(usersRepository.findById(technicianId)).thenReturn(Optional.empty());
    //then
    assertThrows(TechnicianNotFoundException.class, () -> {
      service.setTechnician(orderId, technicianId);
    });
  }

  @Test
  public void should_change_status_when_order_exists() {
    //given
    ArgumentCaptor<UUID> orderArgument = ArgumentCaptor.forClass(UUID.class);
    ArgumentCaptor<String> statusArgument = ArgumentCaptor.forClass(String.class);
    UUID orderId = UUID.randomUUID();
    String status = "status";
    //when
    when(orderRepository.findById(orderId)).thenReturn(Optional.of(OrderEntity.builder().build()));
    service.changeStatus(orderId, status);
    //then
    verify(orderRepository).changeStatus(orderArgument.capture(), statusArgument.capture());
    assertEquals(orderId, orderArgument.getValue());
    assertEquals(status, statusArgument.getValue());
  }

  @Test
  public void should_set_invoice_id_when_order_exists() {
    //given
    ArgumentCaptor<UUID> orderArgument = ArgumentCaptor.forClass(UUID.class);
    ArgumentCaptor<Long> invoiceArgument = ArgumentCaptor.forClass(Long.class);
    UUID order = UUID.randomUUID();
    long invoice = 1L;
    //when
    when(orderRepository.findById(order)).thenReturn(Optional.of(OrderEntity.builder().build()));
    service.setInvoiceId(order, invoice);
    //then
    verify(orderRepository).setInvoiceId(orderArgument.capture(), invoiceArgument.capture());
    assertEquals(order, orderArgument.getValue());
    assertEquals(invoice, invoiceArgument.getValue());
  }

  @Test
  public void should_return_order_when_order_exists() {
    //given
    UUID orderId = UUID.randomUUID();
    OrderDao expectedResult = OrderDao.builder().build();
    //when
    when(orderRepository.findById(orderId)).thenReturn(Optional.of(OrderEntity.builder().build()));
    //then
    assertEquals(expectedResult, service.getOrderById(orderId));
  }

  @Test
  public void should_set_technician_id_when_order_and_technician_exist() {
    //given
    ArgumentCaptor<UUID> orderArgument = ArgumentCaptor.forClass(UUID.class);
    ArgumentCaptor<Long> technicianArgument = ArgumentCaptor.forClass(Long.class);
    UUID uuid = UUID.randomUUID();
    long userId=1L;
    long technician = 1L;
    //when
    when(orderRepository.findById(uuid)).thenReturn(Optional.of(OrderEntity.builder().build()));
    when(usersRepository.findById(userId)).thenReturn(Optional.of(UserEntity.builder().build()));
    service.setTechnician(uuid, technician);
    //then
    verify(orderRepository).setTechnician(orderArgument.capture(), technicianArgument.capture());
    assertEquals(uuid, orderArgument.getValue());
    assertEquals(technician, technicianArgument.getValue());
  }

  @Test
  public void should_add_order() {
    //given
    ArgumentCaptor<ClientEntity> clientEntityArgumentCaptor = ArgumentCaptor.forClass(ClientEntity.class);
//    ArgumentCaptor<DeviceEntity> deviceEntityArgumentCaptor = ArgumentCaptor.forClass(DeviceEntity.class);
    ArgumentCaptor<OrderEntity> orderEntityArgumentCaptor = ArgumentCaptor.forClass(OrderEntity.class);
    OrderRequest orderRequest = getRequest();
    InvoiceEntity invoiceEntity = InvoiceEntity.builder().id(1L).build();
    ClientEntity clientEntity = ClientEntity.builder()
        .id(1L)
        .firstname(orderRequest.firstname())
        .phone(orderRequest.phone())
        .lastname(orderRequest.lastname())
        .email(orderRequest.email())
        .build();
    //when
    when(invoiceRepository.save(InvoiceEntity.builder().build())).thenReturn(invoiceEntity);
    when(clientRepository.save(ClientEntity.builder()
        .firstname(orderRequest.firstname())
        .phone(orderRequest.phone())
        .lastname(orderRequest.lastname())
        .email(orderRequest.email())
        .build())).thenReturn(clientEntity);
    service.addOrder(orderRequest);
    //then
    verify(clientRepository).save(clientEntityArgumentCaptor.capture());
    assertEquals(orderRequest.phone(), clientEntityArgumentCaptor.getValue().phone());
    assertEquals(orderRequest.firstname(), clientEntityArgumentCaptor.getValue().firstname());
    assertEquals(orderRequest.lastname(), clientEntityArgumentCaptor.getValue().lastname());
    assertEquals(orderRequest.email(), clientEntityArgumentCaptor.getValue().email());
//    verify(deviceRepository).save(deviceEntityArgumentCaptor.capture());
//    assertEquals(orderRequest.devices().get(1).model(), deviceEntityArgumentCaptor.getValue().model());
//    assertEquals(orderRequest.devices().get(1).device_type(), deviceEntityArgumentCaptor.getValue().device_type());
//    assertEquals(orderRequest.devices().get(1).password(), deviceEntityArgumentCaptor.getValue().password());
//    assertEquals(orderRequest.devices().get(1).battery(), deviceEntityArgumentCaptor.getValue().battery());
//    assertEquals(orderRequest.devices().get(1).orderid(), deviceEntityArgumentCaptor.getValue().orderId());
//    assertEquals(orderRequest.devices().get(1).damages(), deviceEntityArgumentCaptor.getValue().damages());
//    assertEquals(orderRequest.devices().get(1).charger(), deviceEntityArgumentCaptor.getValue().charger());
    verify(orderRepository).save(orderEntityArgumentCaptor.capture());
    assertEquals(invoiceEntity.id(), orderEntityArgumentCaptor.getValue().invoiceId());
    assertEquals("Przyjęte", orderEntityArgumentCaptor.getValue().status());
    assertEquals(orderRequest.details(), orderEntityArgumentCaptor.getValue().details());

  }

  private OrderRequest getRequest() {
    return OrderRequest.builder()
        .lastname("lastname")
        .phone(123456789L)
        .firstname("filrstame")
        .details("details")
        .email("email")
        .devices(List.of(DeviceDto.builder()
            .damages("damgaes")
            .device_type("type")
            .password("pass")
            .charger(false)
            .battery(false)
            .model("model")
            .orderid(UUID.randomUUID())
            .build()))
        .build();
  }
}