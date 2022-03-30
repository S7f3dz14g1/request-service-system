package com.szwedo.request.service.system.model;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record OrderDetailsDao(UUID id,
                              String status,
                              String details,
                              InvoiceDao invoiceDao,
                              String technicianName,
                              List<DeviceDto> deviceDtoList,
                              ClientDao clientDto,
                              LocalDate createdDate,
                              String doneWork,
                              LocalDate editedDate) {
  @Builder
  public OrderDetailsDao {
  }
}