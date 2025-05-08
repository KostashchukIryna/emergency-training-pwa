package edu.emergencytrainingpwa.service.category;

import edu.emergencytrainingpwa.dto.category.CategoryAddDto;
import edu.emergencytrainingpwa.dto.category.CategoryDto;

public interface CategoryService {
    CategoryDto createCategory(CategoryAddDto categoryDto);
}
