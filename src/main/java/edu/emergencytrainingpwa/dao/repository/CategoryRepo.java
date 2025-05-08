package edu.emergencytrainingpwa.dao.repository;

import edu.emergencytrainingpwa.dao.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
}
