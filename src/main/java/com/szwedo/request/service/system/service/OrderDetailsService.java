package com.szwedo.request.service.system.service;

import com.szwedo.request.service.system.model.OrderDetailsDao;

import java.util.UUID;

public interface OrderDetailsService {
  OrderDetailsDao getOrderParticular(UUID orderId);
}