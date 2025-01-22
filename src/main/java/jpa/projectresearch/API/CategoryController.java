package jpa.projectresearch.API;

import jpa.projectresearch.Dto.CategoryDto;
import jpa.projectresearch.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long Id) {
        CategoryDto category = categoryService.getCategoryById(Id);
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto createdCategory = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(categoryDto, HttpStatus.CREATED);
    }

    @PutMapping("/{Id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long Id, @RequestBody CategoryDto categoryDto) {
        CategoryDto updatedCategory = categoryService.updateCategory(Id, categoryDto);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long Id) {
        categoryService.deleteCategory(Id);
        return new ResponseEntity<>("Deleted successfully! ", HttpStatus.OK);
    }
}
