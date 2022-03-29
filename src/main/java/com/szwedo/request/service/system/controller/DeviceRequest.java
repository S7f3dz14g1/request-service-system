package com.szwedo.request.service.system.controller;

import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

record DeviceRequest(@NotBlank String device_type,
                     String model,
                     @NotBlank String damages,
                     @NotNull boolean battery,
                     @NotNull boolean charger,
                     @NotBlank String password,
                     @NotNull UUID orderId) {

  @Builder
  public DeviceRequest {
  }
}