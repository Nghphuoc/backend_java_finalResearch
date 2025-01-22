package jpa.projectresearch.Mapper;

import jpa.projectresearch.Dto.CategoryDto;
import jpa.projectresearch.Entity.Category;

public class CategoryMapper {

    public static CategoryDto mapCategory(Category category) {
        return new CategoryDto(
          category.getCategoryId(),
          category.getCategoryName(),
          category.getProducts()
        );
    }

    public static Category mapCategoryDto(CategoryDto categoryDto) {
        return new Category(
                categoryDto.getCategoryId(),
                categoryDto.getCategoryName(),
                categoryDto.getProducts()
        );
    }
}
