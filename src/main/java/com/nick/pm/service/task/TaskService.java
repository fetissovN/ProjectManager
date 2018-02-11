package com.nick.pm.service.task;

import com.nick.pm.DTO.TaskDTO;
import com.nick.pm.entity.Task;

import java.util.List;

public interface TaskService {

    void createTask(Task project);

    List<Task> getAllTasks();

    Task getTaskById(Long id);

    void deleteTaskById(Long id);

    void deleteTask(Task task);

    void updateTask(Task task, Long id);

    void saveNewTask(TaskDTO taskDTO);
}
