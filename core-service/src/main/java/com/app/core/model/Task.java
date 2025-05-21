package com.app.core.model;

import com.app.shared.Identifiable;
import com.app.shared.Skill;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Task implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private Long ownerId;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Skill> skills;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    private Long assignedFreelancerId;

    private Integer estimatedTime;

    private Integer concludedTime;

    private LocalDate estimatedStartDate;

    private LocalDate estimatedFinishDate;

    private LocalDate startedDate;

    private LocalDate finishedDate;

    @Min(0)
    @Max(5)
    private Integer ownerReviewScore;

    private String ownerReviewNote;

}
