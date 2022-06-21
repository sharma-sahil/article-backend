package com.nagarro.training.java.exittest.api;

import com.nagarro.training.java.exittest.entity.Product;
import com.nagarro.training.java.exittest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return this.productService.addProduct(product);
    }

    @GetMapping
    public List<Product> getProducts() {
        return this.productService.getProducts();
    }

}
