package com.szwedo.request.service.system.controller;

import com.szwedo.request.service.system.model.OrderDao;
import com.szwedo.request.service.system.model.OrderDetailsDao;
import com.szwedo.request.service.system.service.OrderDetailsService;
import com.szwedo.request.service.system.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {

  private final OrderService orderService;
  private final OrderDetailsService orderParticularService;

  @PutMapping("/invoice/")
  public void setInvoice(@RequestParam("orderId") Long orderId,
                         @RequestParam("invoiceId") Long invoiceId) {
    orderService.setInvoiceId(orderId, invoiceId);
  }

  @PutMapping("/status/")
  public void changeStatus(@RequestParam(value = "orderId") Long orderId,
                           @RequestParam(value = "status") String status) {
    orderService.changeStatus(orderId, status);
  }

  @GetMapping("/{id}")
  public OrderDao getOrderById(@PathVariable("id") Long orderId) {
    return orderService.getOrderById(orderId);
  }

  @PostMapping("/")
  public void addOrder(@RequestBody OrderRequest request) {
    orderService.addOrder(request);
  }

  @GetMapping("/details/{id}")
  public OrderDetailsDao getOrderDetails(@PathVariable("id") Long orderId) {
    return orderParticularService.getOrderParticular(orderId);
  }

  @PutMapping("/technician/")
  public void setTechnician(@RequestParam(value = "orderId") Long orderId,
                            @RequestParam(value = "technicianId") Long technicianId) {
    orderService.setTechnician(orderId, technicianId);
  }
}