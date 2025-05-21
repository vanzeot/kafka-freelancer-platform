package com.app.core.controller;

import com.app.core.dto.TaskDTO;
import com.app.core.dto.TaskResponseDTO;
import com.app.core.mapper.BaseMapper;
import com.app.core.mapper.TaskMapper;
import com.app.core.model.Task;
import com.app.core.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@Slf4j
public class TaskController extends BaseController<Task, TaskDTO, TaskResponseDTO> {

    private final TaskService taskService;

    public TaskController(TaskService service, TaskMapper mapper) {
        super(service, mapper);
        this.taskService = service;
    }

    @GetMapping("/getAllPublished")
    public ResponseEntity<List<TaskResponseDTO>> findAllPublished() {
        return ResponseEntity.ok(service.findAllDtos());
    }

    @PutMapping("/{taskId}/assign/{freelancerId}")
    public ResponseEntity<?> assignFreelancer(@PathVariable Long taskId, @PathVariable Long freelancerId) {
        log.info("Assigning freelancer {} to task {}", freelancerId, taskId);
        try {
            Task task = this.taskService.assignFreelancer(taskId, freelancerId);
            return ResponseEntity.ok(mapper.toDto(task));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error", e);
            return ResponseEntity.internalServerError().body("Internal error.");
        }

    }

}
