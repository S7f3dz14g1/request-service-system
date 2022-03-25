package com.szwedo.request.service.system.dao;

import com.szwedo.request.service.system.entity.DeviceEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface DeviceRepository extends
    CrudRepository<DeviceEntity, Long> {
  List<DeviceEntity> getDeviceEntitiesByOrderId(@NotNull Long orderid);
}