package jpa.projectresearch.Service.Impl;

import jpa.projectresearch.Dto.CategoryDto;
import jpa.projectresearch.Entity.Category;
import jpa.projectresearch.Entity.Product;
import jpa.projectresearch.Mapper.CategoryMapper;
import jpa.projectresearch.Responsesitory.CategoryRepository;
import jpa.projectresearch.Responsesitory.ProductRepository;
import jpa.projectresearch.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    @Transactional
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(CategoryMapper::mapCategory).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()->new RuntimeException("cannot find category"));
        return CategoryMapper.mapCategory(category);
    }

    @Override
    @Transactional
    public CategoryDto createCategory(CategoryDto category) {
        Category category1 = CategoryMapper.mapCategoryDto(category);

        if(category1.getProducts() != null && !category1.getProducts().isEmpty()) {
            List<Product> managedProducts = category1.getProducts().stream()
                    .map(product -> productRepository.findById(product.getProductId())
                            .orElseThrow(() -> new IllegalArgumentException("Product not found")))
                    .collect(Collectors.toList());
            category1.setProducts(managedProducts);
        }

        Category categorySave = categoryRepository.save(category1);
        return CategoryMapper.mapCategory(categorySave);
    }

    @Override
    @Transactional
    public CategoryDto updateCategory(Long id,CategoryDto category) {
        Category categoryUpdate = categoryRepository.findById(id).orElseThrow(()->new RuntimeException("cannot find category"));
        categoryUpdate.setCategoryName(category.getCategoryName());
        Category categorySave = categoryRepository.save(categoryUpdate);
        return CategoryMapper.mapCategory(categorySave);
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()->new RuntimeException("cannot find category"));
        categoryRepository.delete(category);
    }
}
