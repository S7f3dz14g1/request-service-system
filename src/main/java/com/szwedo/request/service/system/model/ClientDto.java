package com.szwedo.request.service.system.model;

import lombok.Builder;

public record ClientDto(Long id,
                        String firstName,
                        String lastName,
                        String email,
                        Long phone) {

  @Builder
  public ClientDto {
  }
}