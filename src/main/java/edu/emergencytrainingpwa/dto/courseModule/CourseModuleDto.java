package edu.emergencytrainingpwa.dto.courseModule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CourseModuleDto {
    private Long id;

    private String title;

    private String description;

    private String imagePath;

    private int orderNumber;

}
