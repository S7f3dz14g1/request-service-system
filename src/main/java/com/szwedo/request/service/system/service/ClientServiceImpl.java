package com.szwedo.request.service.system.service;

import com.szwedo.request.service.system.dao.ClientRepository;
import com.szwedo.request.service.system.entity.ClientEntity;
import com.szwedo.request.service.system.exception.ClientNotFoundException;
import com.szwedo.request.service.system.model.ClientDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

  private final ClientRepository repository;

  @Override
  public void addUserDetails(ClientEntity entity) {
    repository.save(entity);
  }

  @Override
  public ClientDto getUserDetailsById(Long id) {
    return repository.findById(id)
        .stream()
        .map(entity -> ClientDto.builder()
            .id(entity.id())
            .firstName(entity.firstName())
            .lastName(entity.lastName())
            .email(entity.email())
            .phone(entity.phone())
            .build())
        .findFirst()
        .orElseThrow(() ->
            new ClientNotFoundException(id));
  }
}