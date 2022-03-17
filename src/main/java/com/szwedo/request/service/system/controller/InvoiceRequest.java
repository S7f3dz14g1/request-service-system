package com.szwedo.request.service.system.controller;

import lombok.Builder;

import javax.validation.constraints.NotNull;

public record InvoiceRequest(@NotNull Double price,
                             @NotNull Integer tax,
                             @NotNull Double discount) {
  @Builder
  public InvoiceRequest {
  }
}