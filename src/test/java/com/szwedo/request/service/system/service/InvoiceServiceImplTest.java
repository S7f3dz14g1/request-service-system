package com.szwedo.request.service.system.service;

import com.szwedo.request.service.system.dao.InvoiceRepository;
import com.szwedo.request.service.system.entity.InvoiceEntity;
import com.szwedo.request.service.system.exception.InvoiceNotFoundException;
import com.szwedo.request.service.system.model.InvoiceDao;
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
class InvoiceServiceImplTest {

  @Mock
  private InvoiceRepository repository;
  @InjectMocks
  private InvoiceServiceImpl service;

  @Test
  public void should_get_invoice_by_id_when_invoice_exists() {
    //given
    long invoiceId = 1L;
    InvoiceEntity entity = InvoiceEntity.builder()
        .id(invoiceId)
        .discount(20.0)
        .tax(23)
        .price(155.0).build();
    InvoiceDao expectedResult = InvoiceDao
        .builder()
        .id(invoiceId)
        .discount(20.0)
        .tax(23)
        .price(155.0).build();
    //when
    when(repository.findById(invoiceId)).thenReturn(Optional.of(entity));
    //then
    assertEquals(expectedResult, service.getInvoiceById(invoiceId));
  }

  @Test
  public void should_throw_InvoiceNotFoundException_when_invoice_does_not_exist() {
    //given
    ArgumentCaptor<InvoiceEntity> argument=ArgumentCaptor.forClass(InvoiceEntity.class);
    InvoiceEntity entity = InvoiceEntity.builder()
        .discount(20.0)
        .tax(23)
        .price(155.0).build();
    //when
    service.updateInvoice(entity);
    //then
    verify(repository).save(argument.capture());
    assertEquals(entity.tax(),argument.getValue().tax());
    assertEquals(entity.discount(),argument.getValue().discount());
    assertEquals(entity.price(),argument.getValue().price());
  }

  @Test
  public void should_add_device() {
    //given
    long invoiceId = 1L;
    //when
    when(repository.findById(invoiceId)).thenReturn(Optional.empty());
    //then
    assertThrows(InvoiceNotFoundException.class, ()->
        service.getInvoiceById(invoiceId));
  }
}