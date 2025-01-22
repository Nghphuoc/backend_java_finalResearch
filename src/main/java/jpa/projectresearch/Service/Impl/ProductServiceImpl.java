package jpa.projectresearch.Service.Impl;

import jpa.projectresearch.Dto.ProductDto;
import jpa.projectresearch.Entity.Category;
import jpa.projectresearch.Entity.Product;
import jpa.projectresearch.Mapper.ProductMapper;
import jpa.projectresearch.Responsesitory.CategoryRepository;
import jpa.projectresearch.Responsesitory.ProductRepository;
import jpa.projectresearch.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    @Transactional
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductMapper::mapProduct).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(()->new RuntimeException("cannot find product with id: " + id));
        return ProductMapper.mapProduct(product);
    }

    @Override
    @Transactional
    public ProductDto createProduct(ProductDto productDto) {

        Product product = ProductMapper.mapProductDto(productDto);
        // fix here
        if(product.getCategories() != null && !product.getCategories().isEmpty()) {
            List<Category> categories = product.getCategories().stream()
                    .map(category -> categoryRepository.findById(category.getCategoryId())
                            .orElseThrow(() -> new IllegalArgumentException("Product not found")))
                    .collect(Collectors.toList());
            product.setCategories(categories);
        }
        Product savedProduct = productRepository.save(product);
        return ProductMapper.mapProduct(savedProduct);
    }

    @Override
    @Transactional
    public ProductDto updateProduct(Long id,ProductDto productDto) {
        Product productUpdate = productRepository.findById(id).orElseThrow(()->new RuntimeException("cannot find product with id: " + id));
        productUpdate.setProductName(productDto.getProductName());
        productUpdate.setDescription(productDto.getDescription());
        productUpdate.setPrice(productDto.getPrice());
        productUpdate.setCategories(productDto.getCategories());
        productUpdate.setImageUrl(productDto.getImageUrl());
        productUpdate.setStock_quantity(productDto.getStock_quantity());
        productUpdate.setCarts(productDto.getCarts());
        productUpdate.setCategories(productDto.getCategories());
        productUpdate.setOrders(productDto.getOrders());
        Product savedProduct = productRepository.save(productUpdate);
        return ProductMapper.mapProduct(savedProduct);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(()->new RuntimeException("cannot find product with id: " + id));
        productRepository.delete(product);
    }

    @Override
    @Transactional
    public List<Product> findProductName(String name) {
        return (productRepository.findByProductName(name));
    }
}
