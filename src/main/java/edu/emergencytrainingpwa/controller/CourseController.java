package edu.emergencytrainingpwa.controller;

import edu.emergencytrainingpwa.dto.course.*;
import edu.emergencytrainingpwa.dto.pageable.PageableDto;
import edu.emergencytrainingpwa.service.course.CourseService;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping()
    public ResponseEntity<PageableDto<CourseCardDto>> getCourses(Pageable pageable, @RequestBody FilterCourseDto filter) {
        return ResponseEntity.ok().body(courseService.getCourses(pageable, filter));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDetailDto> getCourse(@PathVariable Long id) {
        return ResponseEntity.ok().body(courseService.getCourseById(id));
    }
}
