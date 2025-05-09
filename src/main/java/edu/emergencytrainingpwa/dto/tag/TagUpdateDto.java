package edu.emergencytrainingpwa.dto.tag;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class TagUpdateDto {
    @NotBlank
    private String title;
}
