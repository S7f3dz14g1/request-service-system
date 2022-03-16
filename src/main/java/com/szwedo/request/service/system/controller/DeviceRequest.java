package com.szwedo.request.service.system.controller;

import lombok.Builder;

import javax.validation.constraints.NotBlank;

record DeviceRequest(@NotBlank String device_type,
                     @NotBlank String model,
                     @NotBlank String destroys) {

  @Builder
  public DeviceRequest {
  }
}
