package com.szwedo.request.service.system.controller;

import com.szwedo.request.service.system.entity.InvoiceEntity;
import com.szwedo.request.service.system.model.InvoiceDao;
import com.szwedo.request.service.system.service.InvoiceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
class InvoiceControllerTest {

  @Mock
  private InvoiceService service;
  @InjectMocks
  private InvoiceController controller;

  @Test
  public void should_add_invoice() {
    //given
    ArgumentCaptor<InvoiceEntity> argumentCaptor = ArgumentCaptor.forClass(InvoiceEntity.class);
    InvoiceRequest request = InvoiceRequest.builder()
        .discount(10.0)
        .tax(23)
        .price(10.0)
        .build();
    //when
    controller.addInvoice(request);
    //then
    verify(service).addDevice(argumentCaptor.capture());
    assertEquals(request.discount(), argumentCaptor.getValue().discount());
    assertEquals(request.tax(), argumentCaptor.getValue().tax());
    assertEquals(request.price(), argumentCaptor.getValue().price());
  }

  @Test
  public void should_return_invoice_when_invoice_exists() {
    //given
    long invoiceId = 1L;
    InvoiceDao dto = InvoiceDao.builder()
        .id(invoiceId)
        .tax(23)
        .discount(10.0)
        .price(12.5)
        .build();
    //when
    when(service.getInvoiceById(invoiceId)).thenReturn(dto);
    //then
    assertEquals(dto,controller.getInvoiceById(invoiceId));
  }
}