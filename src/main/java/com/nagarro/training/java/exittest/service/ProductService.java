package com.nagarro.training.java.exittest.service;

import com.nagarro.training.java.exittest.entity.Product;
import com.nagarro.training.java.exittest.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts() {
        return this.productRepository.findAll();
    }

    public Product addProduct(Product product) {
        return this.productRepository.save(product);
    }
}
