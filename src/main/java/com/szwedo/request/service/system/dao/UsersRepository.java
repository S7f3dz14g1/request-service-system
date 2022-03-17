package com.szwedo.request.service.system.dao;

import com.szwedo.request.service.system.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository
    extends CrudRepository<UserEntity, Long> {
}