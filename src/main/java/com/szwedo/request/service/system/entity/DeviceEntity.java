package com.szwedo.request.service.system.entity;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;

@Table("device")
public record DeviceEntity(@Id Long id,
                           @NotNull String device_type,
                           String model,
                           @NotNull String damages,
                           @NotNull boolean battery,
                           @NotNull boolean charger,
                           @NotNull String password,
                           @Column("orderid")
                           @NotNull Long orderId) {

  @Builder
  public DeviceEntity {
  }
}