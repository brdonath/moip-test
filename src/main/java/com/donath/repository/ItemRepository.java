package com.donath.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.donath.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
