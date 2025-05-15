package edu.emergencytrainingpwa.dto.course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseCardDto {
    private Long    id;
    private String  title;
    private String  preview;
    private String  imagePath;
    private Double  rating;
}
