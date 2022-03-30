package com.szwedo.request.service.system.dao;

import com.szwedo.request.service.system.entity.OrderEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, UUID> {
  @Modifying
  @Query(value = "UPDATE orders  SET  status = :Status WHERE id= :OrderId")
  void changeStatus(UUID OrderId, String Status);

  @Modifying
  @Query(value = "UPDATE orders  SET invoiceId = :orderId WHERE id= :orderId")
  void setInvoiceId(UUID orderId, Long invoiceId);

  @Modifying
  @Query(value = "UPDATE  orders SET technicianId = :technicianId WHERE id= :orderId")
  void setTechnician(UUID orderId, Long technicianId);

  @Modifying
  @Query(value = "UPDATE  orders SET done_work = :doneWork WHERE id= :orderId")
  void setDoneWork(UUID orderId, String doneWork);
}