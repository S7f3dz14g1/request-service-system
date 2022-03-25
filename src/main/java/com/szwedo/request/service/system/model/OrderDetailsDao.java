package com.szwedo.request.service.system.model;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public record OrderDetailsDao(Long id,
                              String status,
                              String details,
                              InvoiceDao invoiceDao,
                              String technicianName,
                              List<DeviceDto> deviceDtoList,
                              ClientDao clientDto,
                              LocalDate createdDate,
                              LocalDate editedDate) {
  @Builder
  public OrderDetailsDao {
  }
}