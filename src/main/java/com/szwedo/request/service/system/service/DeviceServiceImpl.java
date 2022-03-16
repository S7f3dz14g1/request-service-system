package com.szwedo.request.service.system.service;

import com.szwedo.request.service.system.dao.DeviceRepository;
import com.szwedo.request.service.system.entity.DeviceEntity;
import com.szwedo.request.service.system.exception.DeviceNotFoundException;
import com.szwedo.request.service.system.model.DeviceDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class DeviceServiceImpl implements DeviceService{

  private final DeviceRepository repository;

  @Override
  public DeviceDto getDeviceById(Long id) {
      return repository.findById(id).stream()
          .map(deviceEntity -> DeviceDto.builder()
              .id(deviceEntity.id())
              .model(deviceEntity.model())
              .destroys(deviceEntity.destroys())
              .device_type(deviceEntity.device_type())
              .build())
          .findFirst()
          .orElseThrow(()->new DeviceNotFoundException(id));
  }

  @Override
  public void addDevice(DeviceEntity device) {
    repository.save(device);
  }
}