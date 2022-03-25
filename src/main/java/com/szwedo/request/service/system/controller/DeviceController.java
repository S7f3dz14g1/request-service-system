package com.szwedo.request.service.system.controller;

import com.szwedo.request.service.system.entity.DeviceEntity;
import com.szwedo.request.service.system.model.DeviceDto;
import com.szwedo.request.service.system.service.DeviceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/device")
public class DeviceController {

  private final DeviceService service;

  @GetMapping("/{id}")
  public DeviceDto getDeviceById(@PathVariable("id") Long id) {
    return service.getDeviceById(id);
  }

  @PostMapping("/")
  public void addDevice(@Valid @RequestBody DeviceRequest device) {
    service.addDevice(DeviceEntity.builder()
        .device_type(device.device_type())
        .damages(device.damages())
        .model(device.model())
        .battery(device.battery())
        .charger(device.charger())
        .password(device.password())
        .orderId(device.orderId())
        .build());
  }
}