package com.szwedo.request.service.system.entity;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.lang.NonNull;

@Table("device")
public record DeviceEntity(@Id Long id,
                           @NonNull String device_type,
                           String model,
                           String destroys) {

  @Builder
  public DeviceEntity {
  }
}