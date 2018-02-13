package com.nick.pm.service.task;

import com.nick.pm.DTO.TaskDTO;
import com.nick.pm.DTO.UserDTO;
import com.nick.pm.converter.SpringConverterTaskToTaskDTO;
import com.nick.pm.converter.SpringConverterUserToUserDTO;
import com.nick.pm.dao.project.ProjectDAO;
import com.nick.pm.dao.task.TaskDAO;
import com.nick.pm.dao.user.UserDAO;
import com.nick.pm.entity.Project;
import com.nick.pm.entity.Status;
import com.nick.pm.entity.Task;
import com.nick.pm.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDAO taskDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ProjectDAO projectDAO;

    @Autowired
    private SpringConverterUserToUserDTO springConverterUserToUserDTO;

    @Autowired
    private SpringConverterTaskToTaskDTO springConverterTaskToTaskDTO;

    @Override
    public void createTask(Task task) {
        taskDAO.createTask(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskDAO.getAllTasks();
    }

    @Override
    public TaskDTO getTaskById(Long id) {
        Task task = taskDAO.getTaskById(id);
        return springConverterTaskToTaskDTO.convert(task);
    }

    @Override
    public void deleteTaskById(Long id) {
        Task task = taskDAO.getTaskById(id);
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

    @Override
    public List<UserDTO> getDevelopersOfTask(Long taskId) {
        Task task = taskDAO.getTaskById(taskId);
        Set<User> developers = task.getDevelopers();
        List<UserDTO> userDTOs = developers.stream()
                .map(u -> springConverterUserToUserDTO.convert(u)).collect(Collectors.toList());
        return userDTOs;
    }

    @Override
    public void addDeveloperToTask(long developerId, long taskId) {
        User user = userDAO.getUserById(developerId);
        Task task = taskDAO.getTaskById(taskId);
        Set<Task> tasks = user.getTasks();
        tasks.add(task);
        user.setTasks(tasks);

        Set<User> developers = task.getDevelopers();
        developers.add(user);
        task.setDevelopers(developers);
        userDAO.updateUserTasks(user,task);
    }
}

