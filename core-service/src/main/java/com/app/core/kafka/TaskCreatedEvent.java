package com.app.core.kafka;

import com.app.core.model.TaskStatus;
import com.app.shared.Skill;
import lombok.Data;

import java.util.List;

@Data
public class TaskCreatedEvent {
    private Long id;
    private String title;
    private String description;
    private Long ownerId;
    private List<Skill> skills;
    private TaskStatus status;
}
