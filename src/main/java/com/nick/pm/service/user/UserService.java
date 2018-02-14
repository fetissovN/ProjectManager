package com.nick.pm.service.user;


import com.nick.pm.DTO.ProjectDTO;
import com.nick.pm.DTO.UserDTO;
import com.nick.pm.entity.Project;
import com.nick.pm.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    User persistUser(User user);

    long createUser(User user) throws com.nick.pm.utils.mail.MailingException;

    User getUserByEmail(String email);

    User getUserById(Long id);

    void updateUser(User user);

    void deleteUser(User user);

    List<ProjectDTO> getProjectsCreatedByUser(long id);

    List<UserDTO> getAllDevelopers();

    void addDeveloperToProject(long developerId, long projectId);

    List<UserDTO> getAllDevelopersOfProject(Long id);

    List<ProjectDTO> getProjectsUserInvolved(Long userId);

    void confirmUser(Long userId, String encoded);
}
