package edu.emergencytrainingpwa.dto.course;

import edu.emergencytrainingpwa.dto.category.CategoryDto;
import edu.emergencytrainingpwa.dto.courseModule.CourseModuleSimpleDto;
import edu.emergencytrainingpwa.dto.tag.TagDto;
import edu.emergencytrainingpwa.dto.user.SimpleUserDto;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CourseResponseDto {
    private Long id;
    private String title;
    private String description;
    private String preview;
    private String targetUsersText;
    private Boolean published;
    private String imagePath;

    private SimpleUserDto author;
    private CategoryDto category;

    private List<String> benefits;
    private Set<TagDto> tags;
    private List<CourseModuleSimpleDto> modules;
}
