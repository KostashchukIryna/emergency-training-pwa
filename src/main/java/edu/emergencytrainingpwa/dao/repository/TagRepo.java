package edu.emergencytrainingpwa.dao.repository;

import edu.emergencytrainingpwa.dao.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepo extends JpaRepository<Tag, Long> {
}
