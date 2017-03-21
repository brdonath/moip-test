package com.donath.repository;

import com.donath.model.Order;
import com.donath.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {

}
