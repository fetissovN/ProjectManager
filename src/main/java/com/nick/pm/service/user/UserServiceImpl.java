package com.nick.pm.service.user;

import com.nick.pm.DTO.ProjectDTO;
import com.nick.pm.converter.SpringConverterProjectToProjectDTO;
import com.nick.pm.dao.user.UserDAO;
import com.nick.pm.entity.Project;
import com.nick.pm.entity.User;
import com.nick.pm.utils.Utils;
import com.nick.pm.utils.mail.Mailing;
import com.nick.pm.utils.password.PassHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private Utils utils;

    @Autowired
    private SpringConverterProjectToProjectDTO springConverterProjectToProjectDTO;


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
    public void updateUser(User user) {
        if (user.getPassword().equals("")){
            User userDB = userDao.getUserById(user.getId());
            user.setPassword(userDB.getPassword());
            userDao.updateUser(user);
        }else {
            String pass = PassHash.stringPassToHash(user.getPassword());
            user.setPassword(pass);
            userDao.updateUser(user);
        }
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


}
