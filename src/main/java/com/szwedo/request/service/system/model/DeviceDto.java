package com.szwedo.request.service.system.model;

import lombok.Builder;

import java.util.UUID;

public record DeviceDto(Long id,
                        String device_type,
                        String model,
                        String damages,
                        boolean battery,
                        boolean charger,
                        String password,
                        UUID orderid) {

  @Builder
  public DeviceDto {
  }
}