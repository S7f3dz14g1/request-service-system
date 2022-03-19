package com.szwedo.request.service.system.service;

import com.szwedo.request.service.system.entity.ClientEntity;
import com.szwedo.request.service.system.model.ClientDto;

public interface ClientService {
  void addUserDetails(ClientEntity entity);

  ClientDto getUserDetailsById(Long id);
}