package com.szwedo.request.service.system.model;

import lombok.Builder;

public record ClientDao(Long id,
                        String firstName,
                        String lastName,
                        String email,
                        Long phone) {

  @Builder
  public ClientDao {
  }
}