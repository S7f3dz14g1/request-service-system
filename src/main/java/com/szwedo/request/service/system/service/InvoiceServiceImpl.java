package com.szwedo.request.service.system.service;

import com.szwedo.request.service.system.dao.InvoiceRepository;
import com.szwedo.request.service.system.entity.InvoiceEntity;
import com.szwedo.request.service.system.exception.InvoiceNotFoundException;
import com.szwedo.request.service.system.model.InvoiceDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class InvoiceServiceImpl implements InvoiceService {

  private final InvoiceRepository repository;

  @Override
  public InvoiceDao getInvoiceById(Long id) {
    return repository.findById(id)
        .stream()
        .map(entity -> InvoiceDao.builder()
            .id(entity.id())
            .discount(entity.discount())
            .price(entity.price())
            .tax(entity.tax())
            .build())
        .findFirst()
        .orElseThrow(() -> new InvoiceNotFoundException(id));
  }

  @Override
  public void updateInvoice(InvoiceEntity device) {
    repository.save(device);
  }
}