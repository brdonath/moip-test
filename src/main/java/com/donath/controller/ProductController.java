package com.donath.controller;

import com.donath.factory.ProductFactory;
import com.donath.model.Product;
import com.donath.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository repo;

    @Autowired
    public ProductController(ProductRepository repo) {
        this.repo = repo;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Product> findProducts() {
        List<Product> all = repo.findAll();
        if (all.isEmpty()) {
            ProductFactory.createFakeProducts(repo);
        }
        return repo.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Product addProduct(@RequestBody Product product) {
        product.setId(null);
        return repo.saveAndFlush(product);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Product updateProduct(@RequestBody Product updatedProduct, @PathVariable Integer id) {
        updatedProduct.setId(id);
        return repo.saveAndFlush(updatedProduct);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable Integer id) {
        repo.delete(id);
    }
}
