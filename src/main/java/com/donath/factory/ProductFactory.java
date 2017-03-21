package com.donath.factory;

import com.donath.model.Product;
import com.donath.repository.ProductRepository;

import java.math.BigDecimal;

public class ProductFactory {
    public static void createFakeProducts(ProductRepository repo) {
        repo.saveAndFlush(new Product("TV 40", BigDecimal.valueOf(30)));
        repo.saveAndFlush(new Product("Mouse ABC", BigDecimal.valueOf(50)));
        repo.saveAndFlush(new Product("Notebook 4G", BigDecimal.valueOf(60)));
    }
}
