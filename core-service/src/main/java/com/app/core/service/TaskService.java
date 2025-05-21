package com.app.core.service;

import com.app.core.dto.TaskDTO;
import com.app.core.dto.TaskResponseDTO;
import com.app.core.kafka.TaskCreatedEvent;
import com.app.core.kafka.TaskKafkaProducer;
import com.app.core.mapper.TaskMapper;
import com.app.core.model.Task;
import com.app.core.model.TaskStatus;
import com.app.core.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService extends CrudService<Task, TaskDTO, TaskResponseDTO> {

    private final TaskKafkaProducer kafkaProducer;

    public TaskService(TaskRepository repository, TaskMapper mapper, TaskKafkaProducer kafkaProducer) {
        super(repository, mapper);
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public Task create(Task task) {
        if (task.getStatus() != TaskStatus.DRAFT) task.setStatus(TaskStatus.PUBLISHED);
        Task created = super.create(task);

        TaskCreatedEvent event = new TaskCreatedEvent();
        event.setId(created.getId());
        event.setTitle(created.getTitle());
        event.setDescription(created.getDescription());
        event.setOwnerId(created.getOwnerId());
        event.setSkills(created.getSkills());
        event.setStatus(created.getStatus());
        kafkaProducer.publish(event);

        return created;
    }

    @Override
    public void delete(Long id) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task with id " + id + " not found"));

        if (task.getStatus() != TaskStatus.DRAFT) {
            throw new IllegalStateException("Only tasks with DRAFT status can be deleted.");
        }

        super.delete(id);
    }

    public Task assignFreelancer(Long taskId, Long freelancerId) {
        Task task = repository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task with id " + taskId + " not found"));
        task.setAssignedFreelancerId(freelancerId);
        return repository.save(task);
    }
}
