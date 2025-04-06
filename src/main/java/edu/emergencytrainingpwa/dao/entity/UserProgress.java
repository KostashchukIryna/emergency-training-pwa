package edu.emergencytrainingpwa.dao.entity;

import edu.emergencytrainingpwa.enums.ProgressStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Table(name = "user_progresses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class UserProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProgressStatus status; // Статус (NOT_STARTED, IN_PROGRESS, COMPLETED)

    @Column(name = "score")
    private Integer score; // Бали за тест (якщо є)

    @Column(name = "last_accessed")
    private LocalDateTime lastAccessed;

}
