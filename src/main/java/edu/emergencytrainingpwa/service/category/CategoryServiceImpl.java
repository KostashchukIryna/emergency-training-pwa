package edu.emergencytrainingpwa.service.category;

import edu.emergencytrainingpwa.constant.ErrorMessage;
import edu.emergencytrainingpwa.dao.entity.Category;
import edu.emergencytrainingpwa.dao.repository.CategoryRepo;
import edu.emergencytrainingpwa.dto.category.CategoryAddDto;
import edu.emergencytrainingpwa.dto.category.CategoryDto;
import edu.emergencytrainingpwa.dto.category.CategoryUpdateDto;
import edu.emergencytrainingpwa.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;
    private final ModelMapper modelMapper;

    @Override
    public CategoryDto createNewCategory(CategoryAddDto categoryDto) {
        validateTitle(categoryDto.getTitle());

        Category category = Category.builder()
            .title(categoryDto.getTitle())
            .build();
        return modelMapper.map(categoryRepo.save(category), CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryUpdateDto categoryUpdateDto) {
        validateCategoryExists(id);
        validateTitle(categoryUpdateDto.getTitle());
        Category category = Category.builder()
            .id(id)
            .title(categoryUpdateDto.getTitle())
            .build();
        return modelMapper.map(categoryRepo.save(category), CategoryDto.class);
    }

    @Override
    public void deleteCategory(Long id) {
        validateCategoryExists(id);
        categoryRepo.deleteById(id);
    }

    private void validateTitle(String title) {
        if (!StringUtils.hasText(title) || title.length() > 200) {
            throw new IllegalArgumentException(
                "Title is required and must be at most 200 characters."
            );
        }
    }
    
    private void validateCategoryExists(Long id) {
        if (!categoryRepo.existsById(id)) {
            throw new NotFoundException(ErrorMessage.CATEGORY_NOT_FOUND);
        }
    }
}
