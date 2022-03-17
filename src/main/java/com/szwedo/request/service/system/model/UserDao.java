package com.szwedo.request.service.system.model;

import lombok.Builder;

public record UserDao(long id,
                      String nick,
                      String password,
                      String status
) {
  @Builder
  public UserDao {

  }
}
