package com.szwedo.request.service.system.dao;

import com.szwedo.request.service.system.entity.InvoiceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository
    extends CrudRepository<InvoiceEntity, Long> {
}