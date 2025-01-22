package jpa.projectresearch.Service;

import jpa.projectresearch.Dto.CategoryDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CategoryService {

    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryById(Long id);

    CategoryDto createCategory(CategoryDto category);

    CategoryDto updateCategory(Long id,CategoryDto category);

    void deleteCategory(Long id);
}
