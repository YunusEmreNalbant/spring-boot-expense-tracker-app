package com.yunusemrenalbant.expensetracker.controller;

import com.yunusemrenalbant.expensetracker.dto.CategoryDto;
import com.yunusemrenalbant.expensetracker.entity.Category;
import com.yunusemrenalbant.expensetracker.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(
            @RequestBody CategoryDto categoryDto
    ) {
        CategoryDto category = categoryService.createCategory(categoryDto);

        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(
            @PathVariable("id") Long categoryId
    ) {
        CategoryDto category = categoryService.getCategoryById(categoryId);

        return ResponseEntity.ok(category);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categories = categoryService.getAllCategories();

        return ResponseEntity.ok(categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(
            @PathVariable("id") Long categoryId,
            @RequestBody CategoryDto categoryDto
    ) {
        CategoryDto updatedCategory = categoryService.updateCategory(categoryId, categoryDto);

        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(
            @PathVariable("id") Long categoryId
    ) {
        categoryService.deleteCategory(categoryId);

        return ResponseEntity.ok("Category deleted successfully");
    }
}