package com.szwedo.request.service.system.model;

import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

public record OrderDao(UUID id,
                       String status,
                       String details,
                       Long invoiceId,
                       Long technicianId,
                       LocalDate created,
                       LocalDate edited,
                       String doneWork,
                       Long clientId) {
  @Builder
  public OrderDao {
  }
}