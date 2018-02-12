package com.nick.pm.dao.user;


import com.nick.pm.entity.Project;
import com.nick.pm.entity.User;

import java.util.List;

public interface UserDAO {

    Long createUser(User user);

    void persistUser(User user);

    User getUserByEmail(String email);

    void updateUserDevelopers(User user, Project project);

    void updateUser(User user);

    User getUserById(Long id);

    void deleteUser(User user);

    List<Project> getListProjectsCreated(User user);

    List<User> getListDevelopers();

}
