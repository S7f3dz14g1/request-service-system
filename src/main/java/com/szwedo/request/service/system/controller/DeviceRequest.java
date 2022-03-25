package com.szwedo.request.service.system.controller;

import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

record DeviceRequest(@NotBlank String device_type,
                     String model,
                     @NotBlank String damages,
                     @NotNull boolean battery,
                     @NotNull boolean charger,
                     @NotBlank String password,
                     @NotNull Long orderId) {

  @Builder
  public DeviceRequest {
  }
}