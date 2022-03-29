package com.szwedo.request.service.system.dao;

import com.szwedo.request.service.system.entity.UserEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository
    extends CrudRepository<UserEntity, Long> {

  @Query(value = "SELECT  * FROM users WHERE nick= :name")
  Optional<UserEntity> findByName(String name);
}