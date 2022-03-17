package com.szwedo.request.service.system.service;

import com.szwedo.request.service.system.entity.InvoiceEntity;
import com.szwedo.request.service.system.model.InvoiceDao;

public interface InvoiceService {

  InvoiceDao getInvoiceById(Long id);

  void addDevice(InvoiceEntity device);
}