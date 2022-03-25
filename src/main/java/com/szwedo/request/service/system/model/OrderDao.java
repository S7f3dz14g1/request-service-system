package com.szwedo.request.service.system.model;

import lombok.Builder;

import java.time.LocalDate;

public record OrderDao(Long id,
                       String status,
                       String details,
                       Long invoiceId,
                       Long technicianId,
                       LocalDate created,
                       LocalDate edited,
                       Long clientId) {
  @Builder
  public OrderDao {
  }
}