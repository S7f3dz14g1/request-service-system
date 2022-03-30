package com.szwedo.request.service.system.controller;

import com.szwedo.request.service.system.model.DeviceDto;
import lombok.Builder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public record OrderRequest(@NotBlank String firstname,
                           @NotBlank String lastname,
                           @NotBlank String email,
                           @NotNull
                           @Min(value = 7)
                           @Max(value = 9)
                           Long phone,
                           List<DeviceDto> devices,
                           String doneWork,
                           @NotBlank String details) {

  @Builder
  public OrderRequest {
  }
}
