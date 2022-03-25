package com.szwedo.request.service.system.service;

import com.szwedo.request.service.system.entity.ClientEntity;
import com.szwedo.request.service.system.model.ClientDao;

public interface ClientService {
  void addUserDetails(ClientEntity entity);

  ClientDao getUserDetailsById(Long id);
}