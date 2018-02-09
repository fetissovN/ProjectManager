package com.nick.pm.service.project;

import com.nick.pm.DTO.UserDTO;
import com.nick.pm.converter.SpringConverterUserDTOToUser;
import com.nick.pm.dao.project.ProjectDAO;
import com.nick.pm.entity.Project;
import com.nick.pm.entity.Role;
import com.nick.pm.entity.User;
import com.nick.pm.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDAO projectDAO;

    @Autowired
    private UserService userService;

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

}

