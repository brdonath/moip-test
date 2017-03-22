package com.donath.repository;

import com.donath.model.Order;
import com.donath.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    Order findOneByPaymentId(String paymentId);
}
