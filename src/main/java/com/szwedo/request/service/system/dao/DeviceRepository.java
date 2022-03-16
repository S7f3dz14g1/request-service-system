package com.szwedo.request.service.system.dao;

import com.szwedo.request.service.system.entity.DeviceEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends
    CrudRepository<DeviceEntity, Long> {

}
