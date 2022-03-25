package com.szwedo.request.service.system.controller;

import com.szwedo.request.service.system.entity.ClientEntity;
import com.szwedo.request.service.system.model.ClientDao;
import com.szwedo.request.service.system.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/client/")
public class ClientController {

  private final ClientService service;

  @GetMapping("/{id}")
  public ClientDao getUserDetailsById(@PathVariable("id") Long id) {
    return service.getUserDetailsById(id);
  }

  @PostMapping("/")
  public void addUserDetails(@Valid @RequestBody ClientRequest userDetailsRequest) {
    service.addUserDetails(ClientEntity.builder()
        .firstname(userDetailsRequest.firstname())
        .lastname(userDetailsRequest.lastname())
        .email(userDetailsRequest.email())
        .phone(userDetailsRequest.phone())
        .build());
  }
}