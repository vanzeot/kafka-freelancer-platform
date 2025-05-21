package com.app.core.mapper;

import com.app.core.dto.TaskDTO;
import com.app.core.dto.TaskResponseDTO;
import com.app.core.model.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper extends BaseMapper<Task, TaskDTO, TaskResponseDTO> {

    Task toEntity(TaskDTO dto);

    TaskResponseDTO toDto(Task task);
}
