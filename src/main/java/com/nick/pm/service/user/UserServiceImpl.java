package com.nick.pm.service.user;

import com.nick.pm.DTO.ProjectDTO;
import com.nick.pm.DTO.UserDTO;
import com.nick.pm.converter.SpringConverterProjectToProjectDTO;
import com.nick.pm.converter.SpringConverterUserToUserDTO;
import com.nick.pm.dao.project.ProjectDAO;
import com.nick.pm.dao.user.UserDAO;
import com.nick.pm.entity.Project;
import com.nick.pm.entity.User;
import com.nick.pm.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private ProjectDAO projectDAO;

    @Autowired
    private Utils utils;

    @Autowired
    private SpringConverterProjectToProjectDTO springConverterProjectToProjectDTO;

    @Autowired
    private SpringConverterUserToUserDTO springConverterUserToUserDTO;


    @Override
    public User persistUser(User user) {
        userDao.persistUser(user);
        return getUserById(user.getId());
    }

    @Override
    public long createUser(User user) throws com.nick.pm.utils.mail.MailingException {
        org.springframework.security.crypto.password.PasswordEncoder encoder
                = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        return userDao.createUser(user);

    }

    @Override
    public User getUserByEmail(String email) {
        String refactorEmail = utils.stringToLowerCase(email);
        return userDao.getUserByEmail(refactorEmail);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public void updateUser(User user) {

        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }

    @Override
    public List<ProjectDTO> getProjectsCreatedByUser(long id) {
        User user = getUserById(id);
        List<Project> projects = userDao.getListProjectsCreated(user);
        List<ProjectDTO> projectDTOs = new ArrayList<>();
        for (Project p: projects) {
            projectDTOs.add(springConverterProjectToProjectDTO.convert(p));
        }
        return projectDTOs;
    }

    @Override
    public List<UserDTO> getAllDevelopers() {
        List<User> listDevelopers = userDao.getListDevelopers();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User u: listDevelopers){
            UserDTO userDTO = springConverterUserToUserDTO.convert(u);
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    @Override
    public void addDeveloperToProject(long developerId, long projectId) {
        User user = userDao.getUserById(developerId);
        Project project = projectDAO.getProjectById(projectId);
        Set<Project> projects = user.getProjects();
        projects.add(project);
        user.setProjects(projects);

        Set<User> developers = project.getDevelopers();
        developers.add(user);
        project.setDevelopers(developers);
        userDao.updateUserDevelopers(user,project);
    }

    @Override
    public List<UserDTO> getAllDevelopersOfProject(Long id) {
        Project project = projectDAO.getProjectById(id);
        Set<User> developers = project.getDevelopers();
        List<UserDTO> userDTOs = new ArrayList<>();
        for(User u: developers){
            UserDTO userDTO = springConverterUserToUserDTO.convert(u);
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    @Override
    public List<ProjectDTO> getProjectsUserInvolved(Long userId) {
        List<ProjectDTO> projectsList = new ArrayList<>();
        User user = userDao.getUserById(userId);
        Set<Project> projects = user.getProjects();
        projectsList.addAll(projects.stream()
                .map(p -> springConverterProjectToProjectDTO.convert(p)).collect(Collectors.toList()));
        return projectsList;
    }

    @Override
    public void confirmUser(Long userId, String encoded) {
        User user = userDao.getUserById(userId);
        if (user.getActive() == 0){
            org.springframework.security.crypto.password.PasswordEncoder encoder
                    = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
            String email = user.getEmail();
//            String encodedEmail = encoder.encode(email);
            if (encoder.matches(email,encoded)){
                user.setActive(1);
                userDao.updateUser(user);
            }
        }
    }
}
