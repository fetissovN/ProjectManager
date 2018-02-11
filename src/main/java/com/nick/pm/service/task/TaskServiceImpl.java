package com.nick.pm.service.task;

import com.nick.pm.DTO.TaskDTO;
import com.nick.pm.dao.project.ProjectDAO;
import com.nick.pm.dao.task.TaskDAO;
import com.nick.pm.dao.user.UserDAO;
import com.nick.pm.entity.Project;
import com.nick.pm.entity.Status;
import com.nick.pm.entity.Task;
import com.nick.pm.entity.User;
import com.nick.pm.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDAO taskDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ProjectDAO projectDAO;

    @Override
    public void createTask(Task task) {
        taskDAO.createTask(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskDAO.getAllTasks();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskDAO.getTaskById(id);
    }

    @Override
    public void deleteTaskById(Long id) {
        Task task = getTaskById(id);
        taskDAO.deleteTask(task);
    }

    @Override
    public void deleteTask(Task task) {
        taskDAO.deleteTask(task);
    }

    @Override
    public void updateTask(Task task, Long id) {
        taskDAO.updateTask(task);
    }
    @Override
    public void saveNewTask(TaskDTO taskDTO) {
        Project project = projectDAO.getProjectById(taskDTO.getProjectId());
        User user = userDAO.getUserById(taskDTO.getUserId());
        Task task = new Task();
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setProjectId(project);
        task.setUserId(user);
        task.setTaskDate(new Date());
        task.setStatus(Status.VERYFYING);
        createTask(task);
    }
}

