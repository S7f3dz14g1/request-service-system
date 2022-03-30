package com.szwedo.request.service.system.service;

import com.szwedo.request.service.system.controller.OrderRequest;
import com.szwedo.request.service.system.dao.*;
import com.szwedo.request.service.system.entity.ClientEntity;
import com.szwedo.request.service.system.entity.DeviceEntity;
import com.szwedo.request.service.system.entity.InvoiceEntity;
import com.szwedo.request.service.system.entity.OrderEntity;
import com.szwedo.request.service.system.exception.OrderNotFoundException;
import com.szwedo.request.service.system.exception.TechnicianNotFoundException;
import com.szwedo.request.service.system.model.OrderDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final UsersRepository usersRepository;
  private final ClientRepository clientRepository;
  private final DeviceRepository deviceRepository;
  private final InvoiceRepository invoiceRepository;

  @Override
  public void changeStatus(UUID orderId, String status) {
    if (orderRepository.findById(orderId).isPresent()) {
      orderRepository.changeStatus(orderId, status);
    } else {
      throw new OrderNotFoundException(orderId);
    }
  }

  @Override
  public void setInvoiceId(UUID orderId, Long invoiceId) {
    if (orderRepository.findById(orderId).isPresent()) {
      orderRepository.setInvoiceId(orderId, invoiceId);
    } else {
      throw new OrderNotFoundException(orderId);
    }
  }

  @Override
  public void addOrder(OrderRequest request) {
    ClientEntity clientEntity = clientRepository.save(ClientEntity.builder()
        .lastname(request.lastname())
        .firstname(request.firstname())
        .email(request.email())
        .phone(request.phone())
        .build());
    request.devices().stream()
        .map(device ->
            deviceRepository.save(DeviceEntity.builder()
                .damages(device.damages())
                .device_type(device.device_type())
                .password(device.password())
                .charger(device.charger())
                .battery(device.battery())
                .model(device.model())
                .orderId(device.orderid())
                .build()
            ));
    InvoiceEntity invoiceEntity = invoiceRepository.save(InvoiceEntity.builder().build());
    orderRepository.save(OrderEntity.builder()
        .createdate(LocalDate.now())
        .editeddate(LocalDate.now())
        .details(request.details())
        .invoiceId(invoiceEntity.id())
        .technicianId(1L)
        .doneWork(request.doneWork())
        .status("Przyjęte")
        .clientId(clientEntity.id())
        .build());
  }

  @Override
  public void setTechnician(UUID orderId, Long technicianId) {
    if (orderRepository.findById(orderId).isPresent()) {
      if (usersRepository.findById(technicianId).isPresent()) {
        orderRepository.setTechnician(orderId, technicianId);
      } else {
        throw new TechnicianNotFoundException(technicianId);
      }
    } else {
      throw new OrderNotFoundException(orderId);
    }
  }

  @Override
  public void setDoneWork(UUID orderId, String doneWork) {
    if (orderRepository.findById(orderId).isPresent()) {
      orderRepository.setDoneWork(orderId, doneWork);
    } else {
      throw new OrderNotFoundException(orderId);
    }
  }

  @Override
  public OrderDao getOrderById(UUID orderId) {
    return orderRepository.findById(orderId).stream()
        .map(e -> OrderDao.builder()
            .id(e.id())
            .clientId(e.clientId())
            .details(e.details())
            .created(e.createdate())
            .edited(e.editeddate())
            .status(e.status())
            .technicianId(e.technicianId())
            .build())
        .findFirst()
        .orElseThrow(() -> new OrderNotFoundException(orderId));
  }
}