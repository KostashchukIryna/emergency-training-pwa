package edu.emergencytrainingpwa.dto.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class CourseAddDto {
    @NotBlank
    @Size(max = 200)
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String preview;

    @NotBlank
    private String targetUsersText;

    @NotNull
    private Boolean published;

    @Size(max = 400)
    private String imagePath;

    @NotNull
    private Long categoryId;

    private List<Long> tagIds;

    @NotEmpty
    private List<@NotBlank String> benefits;
}
