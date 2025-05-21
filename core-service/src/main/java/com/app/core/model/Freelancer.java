package com.app.core.model;

import com.app.shared.Skill;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

import java.util.List;

@Entity
public class Freelancer extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Skill> skills;

    @DecimalMin(value = "0.0")
    @DecimalMax(value = "5.0")
    private float averageRating;
}
