package edu.emergencytrainingpwa.service.course;

import edu.emergencytrainingpwa.dao.entity.Course;
import edu.emergencytrainingpwa.dto.course.CourseAddDto;
import edu.emergencytrainingpwa.dto.course.CourseResponseDto;

public interface CourseService {
    CourseResponseDto addNewCourse(CourseAddDto course, String userEmail);
}
