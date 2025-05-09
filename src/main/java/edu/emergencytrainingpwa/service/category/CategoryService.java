package edu.emergencytrainingpwa.service.category;

import edu.emergencytrainingpwa.dto.category.CategoryAddDto;
import edu.emergencytrainingpwa.dto.category.CategoryDto;
import edu.emergencytrainingpwa.dto.category.CategoryUpdateDto;

public interface CategoryService {
    CategoryDto createNewCategory(CategoryAddDto categoryDto);

    CategoryDto updateCategory(Long id, CategoryUpdateDto categoryUpdateDto);

    void deleteCategory(Long id);
}
