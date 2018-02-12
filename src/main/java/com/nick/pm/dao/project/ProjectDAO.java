package com.nick.pm.dao.project;


import com.nick.pm.DTO.UserDTO;
import com.nick.pm.entity.Project;
import com.nick.pm.entity.Task;
import com.nick.pm.entity.User;

import java.util.List;

public interface ProjectDAO {

    void createProject(Project post);

    List<Project> getAllProjects();

    List<Project> getAllProjectsOfManager(User user);

    Project getProjectById(Long postId);

    void updateProject(Project post);

    void deleteProject(Project post);

    List<Task> getTasksOfProject(Project project);

}
