package com.szwedo.request.service.system.service;

import com.szwedo.request.service.system.controller.OrderRequest;
import com.szwedo.request.service.system.model.OrderDao;

public interface OrderService {
  void changeStatus(Long orderId, String status);

  void setInvoiceId(Long orderId, Long invoiceId);

  void addOrder(OrderRequest orderRequest);

  void setTechnician(Long orderId, Long technicianId);

  OrderDao getOrderById(Long orderId);
}