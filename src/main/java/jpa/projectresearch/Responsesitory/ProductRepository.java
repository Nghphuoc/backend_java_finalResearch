package jpa.projectresearch.Responsesitory;

import jpa.projectresearch.Dto.ProductDto;
import jpa.projectresearch.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
     List<Product> findByProductName(String name);
}
