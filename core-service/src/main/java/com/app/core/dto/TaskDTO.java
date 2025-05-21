package com.app.core.dto;

import com.app.core.model.TaskStatus;
import com.app.shared.Skill;
import lombok.Data;
import java.util.List;

@Data
public class TaskDTO {
    private String title;
    private String description;
    private Long ownerId;
    private List<Skill> skills;
    private TaskStatus status;
}
