package com.szwedo.request.service.system.service;

import com.szwedo.request.service.system.controller.OrderRequest;
import com.szwedo.request.service.system.model.OrderDao;

import java.util.UUID;

public interface OrderService {
  void changeStatus(UUID orderId, String status);

  void setInvoiceId(UUID orderId, Long invoiceId);

  void addOrder(OrderRequest orderRequest);

  void setTechnician(UUID orderId, Long technicianId);

  void setDoneWork(UUID orderId, String doneWork);

  OrderDao getOrderById(UUID orderId);
}