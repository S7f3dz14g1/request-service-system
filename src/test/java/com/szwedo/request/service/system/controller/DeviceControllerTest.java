package com.szwedo.request.service.system.controller;

import com.szwedo.request.service.system.entity.DeviceEntity;
import com.szwedo.request.service.system.model.DeviceDto;
import com.szwedo.request.service.system.service.DeviceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeviceControllerTest {

  @Mock
  private DeviceService service;

  @InjectMocks
  private DeviceController controller;

  @Test
  public void should_return_device() {
    //given
    long deviceId = 1L;
    DeviceDto dto = DeviceDto.builder()
        .id(deviceId)
        .device_type("type")
        .damages("dest")
        .battery(true)
        .charger(true)
        .password("")
        .orderid(UUID.randomUUID())
        .model("model")
        .build();
    //when
    when(service.getDeviceById(deviceId)).thenReturn(dto);
    //then
    assertEquals(controller.getDeviceById(deviceId), dto);
  }

  @Test
  public void should_add_new_device() {
    //given
    ArgumentCaptor<DeviceEntity> argumentCaptor = ArgumentCaptor.forClass(DeviceEntity.class);
    UUID uuid = UUID.randomUUID();
    DeviceRequest dto = DeviceRequest.builder()
        .device_type("type")
        .damages("dest")
        .battery(true)
        .charger(true)
        .password("")
        .orderId(uuid)
        .model("model")
        .build();
    //when
    controller.addDevice(dto);
    //then
    verify(service).addDevice(argumentCaptor.capture());
    assertEquals(argumentCaptor.getValue().device_type(), "type");
    assertEquals(argumentCaptor.getValue().damages(), "dest");
    assertEquals(argumentCaptor.getValue().orderId(), uuid);
    assertEquals(argumentCaptor.getValue().model(), "model");
    assertTrue(argumentCaptor.getValue().battery());
    assertTrue(argumentCaptor.getValue().charger());
    assertEquals(argumentCaptor.getValue().password(), "");
    assertEquals(argumentCaptor.getValue().orderId(), uuid);
  }
}