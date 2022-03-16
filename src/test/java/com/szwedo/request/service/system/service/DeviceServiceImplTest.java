package com.szwedo.request.service.system.service;

import com.szwedo.request.service.system.dao.DeviceRepository;
import com.szwedo.request.service.system.entity.DeviceEntity;
import com.szwedo.request.service.system.exception.DeviceNotFoundException;
import com.szwedo.request.service.system.model.DeviceDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeviceServiceImplTest {

  @Mock
  private DeviceRepository repository;
  @InjectMocks
  private DeviceServiceImpl service;

  @Test
  public void should_return_device_when_device_exists() {
    //given
    long deviceId = 1L;
    DeviceDto expectedDevice = DeviceDto.builder()
        .id(deviceId)
        .device_type("type")
        .destroys("dest")
        .model("model")
        .build();
    DeviceEntity entity = DeviceEntity.builder()
        .id(deviceId)
        .device_type("type")
        .destroys("dest")
        .model("model")
        .build();
    //when
    when(repository.findById(deviceId)).thenReturn(Optional.of(entity));
    //then
    assertEquals(service.getDeviceById(deviceId),expectedDevice);
  }

  @Test
  public void should_throw_DeviceNotFoundException_when_device_does_not_exist() {
    //given
    long deviceId = 1L;
    //when
    when(repository.findById(deviceId)).thenReturn(Optional.empty());
    //then
    assertThrows(DeviceNotFoundException.class,()->{
      service.getDeviceById(deviceId);
    });
  }

  @Test
  public void should_add_new_device(){
    //given
    ArgumentCaptor<DeviceEntity> argument=ArgumentCaptor.forClass(DeviceEntity.class);
    DeviceEntity entity=DeviceEntity.builder()
        .id(1L)
        .device_type("type")
        .destroys("dest")
        .model("model")
        .build();
    //when
    service.addDevice(entity);
    //then
    verify(repository).save(argument.capture());
    assertEquals(entity.id(),argument.getValue().id());
    assertEquals(entity.destroys(),argument.getValue().destroys());
    assertEquals(entity.device_type(),argument.getValue().device_type());
    assertEquals(entity.model(),argument.getValue().model());
  }
}