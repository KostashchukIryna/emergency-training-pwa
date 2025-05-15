package edu.emergencytrainingpwa.dao.entity;

import edu.emergencytrainingpwa.enums.ProgressStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "article_progresses")
public class ArticleProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Article article;

    @Enumerated(EnumType.STRING)
    private ProgressStatus status;

    private LocalDateTime lastAccessed;
}
