package com.nick.pm.service.project;

import com.nick.pm.DTO.TaskDTO;
import com.nick.pm.DTO.UserDTO;
import com.nick.pm.entity.Project;
import com.nick.pm.entity.Task;
import com.nick.pm.entity.User;

import java.util.List;

public interface ProjectService {

    void createProject(Project project);

    List<Project> getAllProjects();

    List<Project> getAllProjectsCreatedByManager(UserDTO userDTO);

    List<Project> getAllProjectsDeveloperTakePart(UserDTO userDTO);

    Project getProjectById(Long id);

    void deleteProjectById(Long id);

    void deleteProject(Project project);

    void updateProject(Project project, Long id);

    void saveNewProject(Project project, Long userId);

    List<TaskDTO> getTasksOfProject(Long projectId);
}
