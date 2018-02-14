package com.nick.pm.service.project;

import com.nick.pm.DTO.TaskDTO;
import com.nick.pm.DTO.UserDTO;
import com.nick.pm.converter.SpringConverterTaskToTaskDTO;
import com.nick.pm.converter.SpringConverterUserDTOToUser;
import com.nick.pm.dao.project.ProjectDAO;
import com.nick.pm.dao.user.UserDAO;
import com.nick.pm.entity.Project;
import com.nick.pm.entity.Role;
import com.nick.pm.entity.Task;
import com.nick.pm.entity.User;
import com.nick.pm.service.task.TaskService;
import com.nick.pm.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDAO projectDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private SpringConverterTaskToTaskDTO springConverterTaskToTaskDTO;

    @Override
    public void createProject(Project project) {
        projectDAO.createProject(project);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectDAO.getAllProjects();
    }

    @Override
    public List<Project> getAllProjectsCreatedByManager(UserDTO userDTO) {
        User user = userService.getUserById(userDTO.getId());
        return projectDAO.getAllProjectsOfManager(user);
    }

    @Override
    public List<Project> getAllProjectsDeveloperTakePart(UserDTO userDTO) {
        if (userDTO.getRole().equals(Role.DEVELOPER.toString())){
            User user = userService.getUserById(userDTO.getId());
            return user.getUserProjects();
        }else{
            return null;
        }
    }

    @Override
    public List<TaskDTO> getTasksOfProject(Long projectId) {
        List<TaskDTO> taskDTOs = new ArrayList<>();
        Project project = getProjectById(projectId);
        List<Task> taskList = projectDAO.getTasksOfProject(project);
        taskDTOs.addAll(taskList.stream().map(t -> springConverterTaskToTaskDTO.convert(t)).collect(Collectors.toList()));
        return taskDTOs;
    }

    @Override
    public List<TaskDTO> getTasksOfProjectForUser(Long projectId, Long userId) {
        List<TaskDTO> taskDTOs = new ArrayList<>();
        Project project = getProjectById(projectId);

        List<Task> taskList = userDAO.getTasksOfProjectForUser(userId, project);
        taskDTOs.addAll(taskList.stream().map(t -> springConverterTaskToTaskDTO.convert(t)).collect(Collectors.toList()));
        return taskDTOs;
    }

    @Override
    public Project getProjectById(Long id) {
        return projectDAO.getProjectById(id);
    }

    @Override
    public void deleteProjectById(Long id) {
        Project project = getProjectById(id);
        projectDAO.deleteProject(project);
    }

    @Override
    public void deleteProject(Project project) {
        projectDAO.deleteProject(project);
    }

    @Override
    public void updateProject(Project project, Long id) {
        projectDAO.updateProject(project);
    }

    @Override
    public void saveNewProject(Project project, Long userId) {
        User user = userService.getUserById(userId);
        if (user.getRole() == Role.MANAGER){
            project.setId(null);
            project.setProjectDate(new Date());
            project.setUserId(user);
            createProject(project);
        }
    }

}

