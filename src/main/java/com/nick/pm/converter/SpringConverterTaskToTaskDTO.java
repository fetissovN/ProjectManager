package com.nick.pm.converter;

import com.nick.pm.DTO.TaskDTO;
import com.nick.pm.entity.Task;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class SpringConverterTaskToTaskDTO implements Converter<Task, TaskDTO> {

    @Override
    public TaskDTO convert(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setName(task.getName());
        taskDTO.setStatus(task.getStatus().toString());
        taskDTO.setUserId(task.getUserId().getId());
        taskDTO.setProjectId(task.getProjectId().getId());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setTaskDate(task.getTaskDate());
        return taskDTO;
    }
}
