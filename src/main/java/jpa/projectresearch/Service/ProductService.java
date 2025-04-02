package jpa.projectresearch.Service;

import jpa.projectresearch.Dto.ProductDto;
import jpa.projectresearch.Entity.Product;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public interface ProductService {

    List<ProductDto> getAllProducts();

    ProductDto getProductById(Long id);

    ProductDto createProduct(ProductDto productDto);

    ProductDto updateProduct(Long id,ProductDto productDto);

    void deleteProduct(Long id);

    void updateSale(List<Long> listNumber);

    List<Product> findByProductNameContainingIgnoreCase(String name);

}
