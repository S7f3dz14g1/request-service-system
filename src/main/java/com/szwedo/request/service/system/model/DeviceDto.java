package com.szwedo.request.service.system.model;

import lombok.Builder;

import javax.validation.constraints.NotBlank;

public record DeviceDto(Long id,
                        @NotBlank String device_type,
                        @NotBlank String model,
                        @NotBlank String destroys) {

  @Builder
  public DeviceDto {
  }
}