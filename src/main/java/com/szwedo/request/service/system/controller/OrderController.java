package com.szwedo.request.service.system.controller;

import com.szwedo.request.service.system.model.OrderDao;
import com.szwedo.request.service.system.model.OrderDetailsDao;
import com.szwedo.request.service.system.service.OrderDetailsService;
import com.szwedo.request.service.system.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {

  private final OrderService orderService;
  private final OrderDetailsService orderDetailsService;

  @PutMapping("/invoice/")
  public void setInvoice(@RequestParam("orderId") UUID orderId,
                         @RequestParam("invoiceId") Long invoiceId) {
    orderService.setInvoiceId(orderId, invoiceId);
  }

  @PutMapping("/status/")
  public void changeStatus(@RequestParam(value = "orderId") UUID orderId,
                           @RequestParam(value = "status") String status) {
    orderService.changeStatus(orderId, status);
  }

  @GetMapping("/{id}")
  public OrderDao getOrderById(@PathVariable("id") UUID orderId) {
    return orderService.getOrderById(orderId);
  }

  @PostMapping("/")
  public void addOrder(@RequestBody OrderRequest request) {
    orderService.addOrder(request);
  }

  @GetMapping("/details/{id}")
  public OrderDetailsDao getOrderDetails(@PathVariable("id") UUID orderId) {
    return orderDetailsService.getOrderParticular(orderId);
  }

  @PutMapping("/technician/")
  public void setTechnician(@RequestParam(value = "orderId") UUID orderId,
                            @RequestParam(value = "technicianId") Long technicianId) {
    orderService.setTechnician(orderId, technicianId);
  }

  @PutMapping("/doneWork/")
  public void setDoneWork(@RequestParam(value = "orderId") UUID orderId,
                          @RequestParam(value = "doneWork") String doneWork) {
    orderService.setDoneWork(orderId, doneWork);
  }
}