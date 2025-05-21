package com.app.core.dto;

import com.app.shared.Skill;
import lombok.Data;
import java.util.List;

@Data
public class TaskResponseDTO {
    private Long id;
    private String title;
    private String description;
    private Long ownerId;
    private List<Skill> skills;
    private Long assignedFreelancerId;
}
