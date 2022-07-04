package sharma.sahil.learning.java.article.backend.api;

import sharma.sahil.learning.java.article.backend.entity.Product;
import sharma.sahil.learning.java.article.backend.service.ProductService;
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
