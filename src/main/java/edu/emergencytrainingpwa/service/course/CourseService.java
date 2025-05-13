package edu.emergencytrainingpwa.service.course;

import edu.emergencytrainingpwa.dto.course.*;
import edu.emergencytrainingpwa.dto.pageable.PageableDto;
import org.springframework.data.domain.Pageable;

public interface CourseService {
    CourseResponseDto addNewCourse(CourseAddDto course, String userEmail);

    PageableDto<CourseCardDto> getCourses(Pageable pageable, FilterCourseDto filter);

    CourseDetailDto getCourseById(Long id);
}
