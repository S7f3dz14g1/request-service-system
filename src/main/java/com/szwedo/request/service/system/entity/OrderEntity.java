package com.szwedo.request.service.system.entity;

import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("orders")
public record OrderEntity(@Id Long id,
                          String status,
                          String details,
                          @Column("invoiceid")
                          Long invoiceId,
                          @Column("technicianid")
                          Long technicianId,
                          @CreatedDate LocalDate createdate,
                          @CreatedDate LocalDate editeddate,
                          @Column("clientid")
                          Long clientId) {
  @Builder
  public OrderEntity {
  }
}