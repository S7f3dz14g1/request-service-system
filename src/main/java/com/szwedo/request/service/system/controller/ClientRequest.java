package com.szwedo.request.service.system.controller;

import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record ClientRequest(@NotBlank String firstName,
                            @NotBlank String lastName,
                            @NotBlank String email,
                            @NotNull Long phone) {
  @Builder
  public ClientRequest {
  }
}
