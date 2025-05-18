package edu.emergencytrainingpwa.dao.repository;

import edu.emergencytrainingpwa.dao.entity.RestorePasswordEmail;
import edu.emergencytrainingpwa.dao.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RestorePasswordEmailRepo extends JpaRepository<RestorePasswordEmail, Long> {

    /**
     * Find the active reset-token for a given user.
     */
    Optional<RestorePasswordEmail> findByUser(User user);

    /**
     * Delete the token by entity (you can also call delete(entity) directly).
     */
    @Modifying
    @Transactional
    void deleteByUser(User user);

    /**
     * Look up a token record by its raw token value.
     */
    Optional<RestorePasswordEmail> findByToken(String token);

    /**
     * Delete by user ID in one go (avoids having to load the entity first).
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM RestorePasswordEmail r WHERE r.user.id = :userId")
    void deleteByUserId(@Param("userId") Long userId);

}
