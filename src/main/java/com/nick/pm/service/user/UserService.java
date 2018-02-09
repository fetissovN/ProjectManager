package com.nick.pm.service.user;


import com.nick.pm.entity.User;
import com.nick.pm.utils.exception.MailingException;

import java.util.List;

public interface UserService {

    void persistUser(User user);

    void createUser(User user) throws com.nick.pm.utils.mail.MailingException;

    User getUserByEmail(String email);

    User getUserById(Long id);

    void updateUser(User user);

    void deleteUser(User user);
}
