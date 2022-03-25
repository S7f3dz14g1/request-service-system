package com.szwedo.request.service.system.model;

import lombok.Builder;

public record DeviceDto(Long id,
                        String device_type,
                        String model,
                        String damages,
                        boolean battery,
                        boolean charger,
                        String password,
                        Long orderid) {

  @Builder
  public DeviceDto {
  }
}