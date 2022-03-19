package com.szwedo.request.service.system.entity;

import lombok.Builder;
import org.springframework.data.annotation.Id;

public record ClientEntity(@Id Long id,
                            String firstName,
                            String lastName,
                            String email,
                            Long phone) {

  @Builder
  public ClientEntity {

  }
}