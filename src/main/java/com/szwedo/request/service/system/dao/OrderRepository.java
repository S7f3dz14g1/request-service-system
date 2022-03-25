package com.szwedo.request.service.system.dao;

import com.szwedo.request.service.system.entity.OrderEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
  @Modifying
  @Query(value = "UPDATE orders  SET  status = :Status WHERE id= :OrderId")
  void changeStatus(Long OrderId, String Status);

  @Modifying
  @Query(value = "UPDATE orders  SET invoiceId = :orderId WHERE id= :orderId")
  void setInvoiceId(Long orderId, Long invoiceId);

  @Modifying
  @Query(value = "UPDATE  orders SET technicianId = :technicianId WHERE id= :orderId")
  void setTechnician(Long orderId, Long technicianId);
}