package edu.emergencytrainingpwa.dto.courseModule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CourseModuleSimpleDto {
    private Long id;

    private String title;
}
