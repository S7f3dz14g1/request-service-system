package com.szwedo.request.service.system.service;

import com.szwedo.request.service.system.entity.DeviceEntity;
import com.szwedo.request.service.system.model.DeviceDto;

public interface DeviceService {

  DeviceDto getDeviceById(Long id);

  void addDevice(DeviceEntity device);
}