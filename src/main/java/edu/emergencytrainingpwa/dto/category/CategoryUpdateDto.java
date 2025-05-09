package edu.emergencytrainingpwa.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CategoryUpdateDto {
    @NotBlank
    private String title;
}
