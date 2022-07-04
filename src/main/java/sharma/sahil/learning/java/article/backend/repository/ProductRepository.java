package sharma.sahil.learning.java.article.backend.repository;

import sharma.sahil.learning.java.article.backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
