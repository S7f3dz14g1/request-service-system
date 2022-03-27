package com.szwedo.request.service.system.service;

import com.szwedo.request.service.system.dao.*;
import com.szwedo.request.service.system.entity.*;
import com.szwedo.request.service.system.exception.OrderNotFoundException;
import com.szwedo.request.service.system.model.ClientDao;
import com.szwedo.request.service.system.model.DeviceDto;
import com.szwedo.request.service.system.model.InvoiceDao;
import com.szwedo.request.service.system.model.OrderDetailsDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderDetailsServiceImpl implements OrderDetailsService {

  private final OrderRepository orderRepository;
  private final ClientRepository clientRepository;
  private final UsersRepository usersRepository;
  private final InvoiceRepository invoiceRepository;
  private final DeviceRepository deviceRepository;

  @Override
  public OrderDetailsDao getOrderParticular(Long orderId) {
    Optional<OrderEntity> optionalOrderEntity = orderRepository.findById(orderId);
    if (optionalOrderEntity.isPresent()) {
      List<DeviceEntity> deviceEntityList =
          deviceRepository.getDeviceEntitiesByOrderId(optionalOrderEntity.get().id());
      InvoiceEntity invoiceEntity =
          invoiceRepository.findById(optionalOrderEntity.get().invoiceId())
              .orElse(InvoiceEntity.builder().build());
      UserEntity userEntity =
          usersRepository.findById(optionalOrderEntity.get().technicianId())
              .orElse(UserEntity.builder().build());
      ClientEntity clientEntity =
          clientRepository.findById(optionalOrderEntity.get().clientId())
              .orElse(ClientEntity.builder().build());
      return buildOrderParticularDao(optionalOrderEntity.get(),
          deviceEntityList,
          invoiceEntity,
          userEntity,
          clientEntity);
    } else {
      throw new OrderNotFoundException(orderId);
    }
  }

  private OrderDetailsDao buildOrderParticularDao(OrderEntity orderEntity, List<DeviceEntity> deviceEntityList, InvoiceEntity invoiceEntity, UserEntity userEntity, ClientEntity clientEntity) {
    return OrderDetailsDao.builder()
        .id(orderEntity.clientId())
        .status(orderEntity.status())
        .createdDate(orderEntity.createdate())
        .details(orderEntity.details())
        .editedDate(orderEntity.editeddate())
        .invoiceDao(buildInvoiceDao(invoiceEntity))
        .technicianName(userEntity.nick())//valid
        .clientDto(buildClientDao(clientEntity))
        .deviceDtoList(deviceEntityList.stream()
            .map(this::buildDeviceDao)
            .collect(Collectors.toList()))
        .build();
  }

  private InvoiceDao buildInvoiceDao(InvoiceEntity invoiceEntityOptional) {
    return InvoiceDao.builder()
        .id(invoiceEntityOptional.id())
        .discount(invoiceEntityOptional.discount())
        .price(invoiceEntityOptional.price())
        .tax(invoiceEntityOptional.tax())
        .build();
  }

  private ClientDao buildClientDao(ClientEntity clientEntity) {
    return ClientDao.builder()
        .id(clientEntity.id())
        .email(clientEntity.email())
        .phone(clientEntity.phone())
        .lastName(clientEntity.lastname())
        .firstName(clientEntity.lastname())
        .build();
  }

  private DeviceDto buildDeviceDao(DeviceEntity deviceEntity) {
    return DeviceDto.builder()
        .id(deviceEntity.id())
        .password(deviceEntity.password())
        .charger(deviceEntity.charger())
        .battery(deviceEntity.battery())
        .device_type(deviceEntity.device_type())
        .damages(deviceEntity.damages())
        .model(deviceEntity.model())
        .orderid(deviceEntity.orderId())
        .build();
  }
}