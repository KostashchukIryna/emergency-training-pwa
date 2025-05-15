package edu.emergencytrainingpwa.dao.entity;

import edu.emergencytrainingpwa.enums.ProgressStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "video_lecture_progresses")
public class VideoLectureProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private VideoLecture videoLecture;

    @Enumerated(EnumType.STRING)
    private ProgressStatus status;

    private LocalDateTime lastAccessed;
}
