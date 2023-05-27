package com.backend.app.repository;

import com.backend.app.model.ProductOrder;
import com.backend.app.model.ProductOrderId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, ProductOrderId> {
}
