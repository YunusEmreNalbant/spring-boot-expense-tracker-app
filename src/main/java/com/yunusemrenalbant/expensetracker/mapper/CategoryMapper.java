package com.yunusemrenalbant.expensetracker.mapper;

import com.yunusemrenalbant.expensetracker.dto.CategoryDto;
import com.yunusemrenalbant.expensetracker.entity.Category;

public class CategoryMapper {

    public static Category mapToCategory(CategoryDto categoryDto) {
        return new Category(
                categoryDto.id(),
                categoryDto.name()
        );
    }

    public static CategoryDto mapToCategoryDto(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getName()
        );
    }
}
