package com.szwedo.request.service.system.controller;

import com.szwedo.request.service.system.entity.UserEntity;
import com.szwedo.request.service.system.model.UserDao;
import com.szwedo.request.service.system.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

  private final UserService service;

  @PostMapping("/")
  public void addUser(@Valid @RequestBody UserRequest request){
    service.addUser(UserEntity.builder()
        .nick(request.nick())
        .password(request.password())
        .status("MODERATOR")
        .build());
  }

  @GetMapping("/{id}")
  public UserDao getUserById(@PathVariable("id")long id){
    return service.getUserById(id);
  }
}
