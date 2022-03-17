package com.szwedo.request.service.system.controller;

import com.szwedo.request.service.system.entity.InvoiceEntity;
import com.szwedo.request.service.system.model.InvoiceDao;
import com.szwedo.request.service.system.service.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/invoice")
@AllArgsConstructor
public class InvoiceController {

  private final InvoiceService service;

  @GetMapping("/{id}")
  public InvoiceDao getInvoiceById(@PathVariable("id") Long id){
    return service.getInvoiceById(id);
  }

  @PostMapping("/")
  public void addInvoice(@Valid @RequestBody InvoiceRequest invoice){
    service.addDevice(InvoiceEntity.builder()
        .tax(invoice.tax())
        .price(invoice.price())
        .discount(invoice.discount())
        .build());
  }
}