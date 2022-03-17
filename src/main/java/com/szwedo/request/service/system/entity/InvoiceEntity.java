package com.szwedo.request.service.system.entity;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("invoice")
public record InvoiceEntity(@Id Long id,
                            Double price,
                            Integer tax,
                            Double discount) {
  @Builder
  public InvoiceEntity {
  }
}