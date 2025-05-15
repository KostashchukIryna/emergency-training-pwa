package edu.emergencytrainingpwa.dao.entity;

import edu.emergencytrainingpwa.enums.ProgressStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "test_progresses")
public class TestProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Test test;

    @Enumerated(EnumType.STRING)
    private ProgressStatus status;

    private Integer score;

    private LocalDateTime lastAccessed;
}
