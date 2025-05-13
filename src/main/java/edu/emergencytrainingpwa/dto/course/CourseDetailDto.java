package edu.emergencytrainingpwa.dto.course;

import edu.emergencytrainingpwa.dto.category.CategoryDto;
import edu.emergencytrainingpwa.dto.courseModule.CourseModuleDto;
import edu.emergencytrainingpwa.dto.tag.TagDto;
import edu.emergencytrainingpwa.dto.user.AuthorDto;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDetailDto {
    private Long id;
    private String title;
    private String description;
    private String preview;
    private String targetUsersText;
    private String imagePath;
    private Double rating;
    private CategoryDto category;
    private AuthorDto author;
    private List<String> benefits;
    private Set<TagDto> tags;
    private List<CourseModuleDto> modules;
    private LocalDateTime createdAt;
}
