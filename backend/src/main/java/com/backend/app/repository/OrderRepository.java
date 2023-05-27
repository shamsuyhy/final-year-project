package com.backend.app.repository;

import com.backend.app.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findOrderByClientPhone(String clientPhone);
    List<Order> findOrderByCurrentStatus(String currentStatus);

    Long countOrderByCurrentStatus(String currentStatus);

}
