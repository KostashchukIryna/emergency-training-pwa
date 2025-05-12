package edu.emergencytrainingpwa.service.course;

import edu.emergencytrainingpwa.dao.entity.Course;
import edu.emergencytrainingpwa.dto.course.CourseAddDto;
import edu.emergencytrainingpwa.dto.course.CourseResponseDto;
import edu.emergencytrainingpwa.dto.course.FilterCourseDto;
import edu.emergencytrainingpwa.dto.pageable.PageableDto;
import org.springframework.data.domain.Pageable;

public interface CourseService {
    CourseResponseDto addNewCourse(CourseAddDto course, String userEmail);

    PageableDto<CourseResponseDto> getCourses(Pageable pageable, FilterCourseDto filter);
}
