package com.szwedo.request.service.system.model;

import lombok.Builder;

public record InvoiceDao(Long id,
                         Double price,
                         Integer tax,
                         Double discount) {
  @Builder
  public InvoiceDao {
  }
}