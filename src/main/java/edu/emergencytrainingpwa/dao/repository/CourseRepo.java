package edu.emergencytrainingpwa.dao.repository;

import edu.emergencytrainingpwa.dao.entity.Course;
import edu.emergencytrainingpwa.dto.course.CourseCardDto;
import edu.emergencytrainingpwa.dto.course.FilterCourseDto;
import jakarta.persistence.Tuple;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {

    @Query("""
      SELECT new edu.emergencytrainingpwa.dto.course.CourseCardDto(
        c.id,
        c.title,
        c.preview,
        c.imagePath,
        c.rating
      )
      FROM Course c
      LEFT JOIN c.tags t
      WHERE
        c.published = true
        AND (:#{#filter.category}   IS NULL OR c.category = :#{#filter.category})
        AND (:#{#filter.author}     IS NULL OR c.author   = :#{#filter.author})
        AND (:#{#filter.tagIds.size()} = 0 OR t.id IN :#{#filter.tagIds})
      GROUP BY
        c.id, c.title, c.preview, c.imagePath, c.rating
    """)
    Page<CourseCardDto> findCourseCardsByFilter(
        @Param("filter") FilterCourseDto filter,
        Pageable pageable
    );

    @EntityGraph(attributePaths = {
        "category",
        "author",
        "tags",
        "modules",
        "benefits"
    })
    Optional<Course> findByIdAndPublishedTrue(Long id);
}
