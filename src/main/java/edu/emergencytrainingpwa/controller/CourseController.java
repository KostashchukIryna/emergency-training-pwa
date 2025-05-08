package edu.emergencytrainingpwa.controller;

import edu.emergencytrainingpwa.dto.course.CourseAddDto;
import edu.emergencytrainingpwa.dto.course.CourseResponseDto;
import edu.emergencytrainingpwa.service.course.CourseService;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
@Validated
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping()
    public ResponseEntity<CourseResponseDto> addCourse(@Validated @RequestBody CourseAddDto course,
                                                       Principal principal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.addNewCourse(course, principal.getName()));
    }
}
