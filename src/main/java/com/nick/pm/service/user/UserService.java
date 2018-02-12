package com.nick.pm.service.user;


import com.nick.pm.DTO.ProjectDTO;
import com.nick.pm.DTO.UserDTO;
import com.nick.pm.entity.User;

import java.util.List;

public interface UserService {

    void persistUser(User user);

    long createUser(User user) throws com.nick.pm.utils.mail.MailingException;

    User getUserByEmail(String email);

    User getUserById(Long id);

    User updateUser(User user);

    void deleteUser(User user);

    List<ProjectDTO> getProjectsCreatedByUser(long id);

    List<UserDTO> getAllDevelopers();

    UserDTO addDeveloperToProject(long developerId, long projectId);
}
