package com.szwedo.request.service.system.entity;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("users")
public record UserEntity(@Id Long id,
                         String nick,
                         String password,
                         String status
) {
  @Builder
  public UserEntity {
  }
}