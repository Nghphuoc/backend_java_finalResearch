package jpa.projectresearch.Responsesitory;

import jpa.projectresearch.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
     List<Product> findByProductName(String name);

     List<Product> findByProductNameContainingIgnoreCase(String name);

     @Modifying
     @Transactional
     @Query(value = "DELETE FROM order_product_quantity WHERE product_id = :productId", nativeQuery = true)
     void deleteProductQuantitiesByProductId(@Param("productId") Long productId);
}
