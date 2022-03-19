package com.szwedo.request.service.system.controller;

import lombok.Builder;

import javax.validation.constraints.NotBlank;

public record UserRequest(@NotBlank String nick,
                          @NotBlank String password,
                          @NotBlank Long userDetailsId) {
  @Builder
  public UserRequest {
  }
}