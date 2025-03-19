package jpa.projectresearch.Service.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    private Cloudinary cloudinary;

    private String uploadImage(MultipartFile file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        return uploadResult.get("secure_url").toString(); // Lấy URL ảnh
    }

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


    public class ProductImageUploadException extends RuntimeException {
        public ProductImageUploadException(String message, Throwable cause) {
            super(message, cause);
        }
    }


    @Override
    @Transactional
    public ProductDto createProduct(ProductDto productDto) {
        // Upload image to Cloudinary and get the image URL
//        String imageUrl;
//        try {
//            imageUrl = uploadImage(file);  // uploadImage() should handle file uploading and return the URL
//        } catch (IOException e) {
//            throw new ProductImageUploadException("Failed to upload product image", e);  // Custom exception for clarity
//        }

        // Map the ProductDto to Product entity
        Product product = ProductMapper.mapProductDto(productDto);
        //product.setImageUrl(productDto.getImageUrl());  // Set image URL in the Product entity

        // Ensure categories are not null and set them properly
        if (product.getCategories() != null && !product.getCategories().isEmpty()) {
            List<Category> categories = product.getCategories().stream()
                    .map(category -> categoryRepository.findById(category.getCategoryId())
                            .orElseThrow(() -> new IllegalArgumentException("Category not found")))
                    .collect(Collectors.toList());  // Using Collectors.toList()
            product.setCategories(categories);
        }
        // Save the product to the database
        Product savedProduct = productRepository.save(product);
        // Return the mapped ProductDto
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
        productUpdate.setCategories(productDto.getCategories());
        Product savedProduct = productRepository.save(productUpdate);
        return ProductMapper.mapProduct(savedProduct);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(()->new RuntimeException("cannot find product with id: " + id));
        productRepository.deleteProductQuantitiesByProductId(id);
        productRepository.delete(product);
    }

    @Override
    @Transactional
    public List<Product> findProductName(String name) {
        return (productRepository.findByProductName(name));
    }

    @Override
    public List<Product> findByProductNameContainingIgnoreCase(String name) {
        return productRepository.findByProductNameContainingIgnoreCase(name);
    }
}
