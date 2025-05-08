package ru.aston.service.category;

import ru.aston.model.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAllCategories();

    CategoryDto addCategory(CategoryDto categoryDto);

    void deleteCategory(Long id);
}