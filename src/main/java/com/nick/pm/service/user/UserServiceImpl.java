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
import java.util.List;


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
    public void persistUser(User user) {
        userDao.persistUser(user);
    }

    @Override
    public long createUser(User user) throws com.nick.pm.utils.mail.MailingException {
        org.springframework.security.crypto.password.PasswordEncoder encoder
                = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        long userCreatedId = userDao.createUser(user);
        return userCreatedId;

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
    public User updateUser(User user) {
//        if (user.getPassword().equals("")){
//            User userDB = userDao.getUserById(user.getId());
//            user.setPassword(userDB.getPassword());
        return userDao.updateUser(user);
//        }else {
//            String pass = PassHash.stringPassToHash(user.getPassword());
//            user.setPassword(pass);
//            userDao.updateUser(user);
//        }
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
    public UserDTO addDeveloperToProject(long developerId, long projectId) {
        User user = userDao.getUserById(developerId);
        Project project = projectDAO.getProjectById(projectId);
        List<Project> projects = user.getProjects();
        projects.add(project);
        user.setProjects(projects);
        User userDb = updateUser(user);
        return springConverterUserToUserDTO.convert(userDb);
    }
}
