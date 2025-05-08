package edu.emergencytrainingpwa.service.category;

import edu.emergencytrainingpwa.dao.entity.Category;
import edu.emergencytrainingpwa.dao.repository.CategoryRepo;
import edu.emergencytrainingpwa.dto.category.CategoryAddDto;
import edu.emergencytrainingpwa.dto.category.CategoryDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;
    private final ModelMapper modelMapper;

    public CategoryDto createCategory(CategoryAddDto categoryDto) {
        validateTitle(categoryDto.getTitle());

        Category category = Category.builder()
            .title(categoryDto.getTitle())
            .build();
        return modelMapper.map(categoryRepo.save(category), CategoryDto.class);
    }

    private void validateTitle(String title) {
        if (!StringUtils.hasText(title) || title.length() > 200) {
            throw new IllegalArgumentException(
                "Title is required and must be at most 200 characters."
            );
        }
    }
}
