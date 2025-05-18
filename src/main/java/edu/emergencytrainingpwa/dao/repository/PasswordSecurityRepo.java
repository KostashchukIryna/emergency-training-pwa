package edu.emergencytrainingpwa.dao.repository;

import edu.emergencytrainingpwa.dao.entity.PasswordSecurity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordSecurityRepo extends JpaRepository<PasswordSecurity, Long> {

    Optional<PasswordSecurity> findByUserId(Long id);
}
