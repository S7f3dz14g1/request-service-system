package com.szwedo.request.service.system.controller;

import lombok.Builder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record ClientRequest(@NotBlank String firstname,
                            @NotBlank String lastname,
                            @NotBlank String email,
                            @NotNull
                            @Min(value = 7)
                            @Max(value = 9)
                            Long phone) {
  @Builder
  public ClientRequest {
  }
}