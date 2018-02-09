package com.nick.pm.dao.user;


import com.nick.pm.entity.User;

public interface UserDAO {

    Long createUser(User user);

    void persistUser(User user);

    User getUserByEmail(String email);

    void updateUser(User user);

    User getUserById(Long id);

    void deleteUser(User user);
}
