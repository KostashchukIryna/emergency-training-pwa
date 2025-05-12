package edu.emergencytrainingpwa.dao.repository;

import edu.emergencytrainingpwa.dao.entity.Course;
import edu.emergencytrainingpwa.dto.course.FilterCourseDto;
import jakarta.persistence.Tuple;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {
    Page<Long> findCourseIds(Pageable pageable, FilterCourseDto filter);

    List<Tuple> loadCourseDataByIds(List<Long> content);
}
