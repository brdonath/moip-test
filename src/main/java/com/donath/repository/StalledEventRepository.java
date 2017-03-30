package com.donath.repository;

import com.donath.model.StalledEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StalledEventRepository extends JpaRepository<StalledEvent, Integer> {

    List<StalledEvent> findByPaymentId(String paymentId);

    Long deleteByPaymentId(String paymentId);
}
