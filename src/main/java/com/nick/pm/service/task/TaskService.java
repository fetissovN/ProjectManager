package com.nick.pm.service.task;

import com.nick.pm.DTO.CommentDTO;
import com.nick.pm.DTO.TaskDTO;
import com.nick.pm.DTO.UserDTO;
import com.nick.pm.entity.Task;

import java.util.List;

public interface TaskService {

    void createTask(Task project);

    List<Task> getAllTasks();

    TaskDTO getTaskById(Long id);

    void deleteTaskById(Long id);

    void deleteTask(Task task);

    void updateTask(Task task, Long id);

    void saveNewTask(TaskDTO taskDTO);

    List<UserDTO> getDevelopersOfTask(Long taskId);

    void addDeveloperToTask(long developerId, long taskId);

    List<CommentDTO> getAllComments(Long taskId);
}
