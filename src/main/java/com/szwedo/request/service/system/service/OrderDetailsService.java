package com.szwedo.request.service.system.service;

import com.szwedo.request.service.system.model.OrderDetailsDao;

public interface OrderDetailsService {
  OrderDetailsDao getOrderParticular(Long orderId);
}