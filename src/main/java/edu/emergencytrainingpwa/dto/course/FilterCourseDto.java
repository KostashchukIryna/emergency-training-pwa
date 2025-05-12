package edu.emergencytrainingpwa.dto.course;

import edu.emergencytrainingpwa.dao.entity.Category;
import edu.emergencytrainingpwa.dao.entity.User;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilterCourseDto {
    private Category category;

    private User author;

    private Set<Long> tagIds;

    public static final String defaultJson = """
        {
          "category": null,
          "author": null,
          "tagIds": []
        }
        """;
}
