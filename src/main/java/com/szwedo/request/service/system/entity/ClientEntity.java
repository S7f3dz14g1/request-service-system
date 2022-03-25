package com.szwedo.request.service.system.entity;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("client")
public record ClientEntity(@Id Long id,
                           String firstname,
                           String lastname,
                           String email,
                           Long phone) {

  @Builder
  public ClientEntity {

  }
}