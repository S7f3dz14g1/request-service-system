package com.szwedo.request.service.system.controller;

import com.szwedo.request.service.system.model.*;
import com.szwedo.request.service.system.service.OrderDetailsService;
import com.szwedo.request.service.system.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

  @Mock
  private OrderService orderService;
  @Mock
  private OrderDetailsService orderDetailsService;
  @InjectMocks
  private OrderController controller;

  @Test
  public void should_set_invoice_in_the_order() {
    //given
    long invoiceId = 1L;
    long orderId = 1L;
    ArgumentCaptor<Long> argumentOrderId = ArgumentCaptor.forClass(Long.class);
    ArgumentCaptor<Long> argumentInvoiceId = ArgumentCaptor.forClass(Long.class);
    //when
    controller.setInvoice(orderId,invoiceId);
    //then
    verify(orderService).setInvoiceId(argumentOrderId.capture(),argumentInvoiceId.capture());
    assertEquals(invoiceId,argumentInvoiceId.getValue());
    assertEquals(orderId,argumentOrderId.getValue());
  }

  @Test
  public void should_change_status_in_the_order() {
    //given
    String status = "status";
    long orderId = 1L;
    ArgumentCaptor<String> argumentStatus = ArgumentCaptor.forClass(String.class);
    ArgumentCaptor<Long> argumentOrderId = ArgumentCaptor.forClass(Long.class);
    //when
    controller.changeStatus(orderId, status);
    //then
    verify(orderService).changeStatus(argumentOrderId.capture(), argumentStatus.capture());
    assertEquals(status, argumentStatus.getValue());
    assertEquals(orderId, argumentOrderId.getValue());
  }

  @Test
  public void should_return_order_by_id() {
    //given
    long orderId = 1L;
    OrderDao detailsDao = OrderDao.builder()
        .id(orderId)
        .details("details")
        .clientId(1L)
        .technicianId(1L)
        .invoiceId(1L)
        .build();
    //when
    when(orderService.getOrderById(orderId)).thenReturn(detailsDao);
    //then
    assertEquals(detailsDao, controller.getOrderById(orderId));
  }

  @Test
  public void should_add_order() {
    //given
    OrderRequest request = OrderRequest.builder()
        .details("details")
        .email("email")
        .firstname("firstname")
        .lastname("lastname")
        .phone(123456789L)
        .devices(List.of(DeviceDto.builder().build()))
        .build();
    ArgumentCaptor<OrderRequest> argument = ArgumentCaptor.forClass(OrderRequest.class);
    //when
    controller.addOrder(request);
    //then
    verify(orderService).addOrder(argument.capture());
    assertEquals(request.details(), argument.getValue().details());
    assertEquals(request.email(), argument.getValue().email());
    assertEquals(request.firstname(), argument.getValue().firstname());
    assertEquals(request.lastname(), argument.getValue().lastname());
    assertEquals(request.phone(), argument.getValue().phone());
    assertEquals(request.devices(), argument.getValue().devices());
  }

  @Test
  public void should_return_order_details() {
    //given
    long orderId = 1L;
    OrderDetailsDao detailsDao = createOrderDetailsDao(orderId);
    //when
    when(orderDetailsService.getOrderParticular(orderId)).thenReturn(detailsDao);
    //then
    assertEquals(controller.getOrderDetails(orderId), detailsDao);
  }

  private OrderDetailsDao createOrderDetailsDao(long orderId) {
    return OrderDetailsDao.builder()
        .id(orderId)
        .details("details")
        .invoiceDao(InvoiceDao.builder().build())
        .clientDto(ClientDao.builder().build())
        .createdDate(LocalDate.now())
        .deviceDtoList(List.of())
        .status("status")
        .editedDate(LocalDate.now())
        .technicianName("name")
        .build();
  }

  @Test
  public void should_set_technician_to_the_order() {
    //given
    long orderId = 1L;
    long technicianId = 1L;
    ArgumentCaptor<Long> argumentOrder = ArgumentCaptor.forClass(Long.class);
    ArgumentCaptor<Long> argumentTechnician = ArgumentCaptor.forClass(Long.class);
    //when
    controller.setTechnician(orderId, technicianId);
    //then
    verify(orderService).setTechnician(argumentOrder.capture(), argumentTechnician.capture());
    assertEquals(orderId, argumentOrder.getValue());
    assertEquals(technicianId, argumentTechnician.getValue());
  }
}