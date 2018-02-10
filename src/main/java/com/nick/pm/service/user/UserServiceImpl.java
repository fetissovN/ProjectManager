package com.nick.pm.service.user;

import com.nick.pm.dao.user.UserDAO;
import com.nick.pm.entity.User;
import com.nick.pm.utils.Utils;
import com.nick.pm.utils.exception.MailingException;
import com.nick.pm.utils.mail.Mailing;
import com.nick.pm.utils.password.PassHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private Mailing mailing;

    @Autowired
    private Utils utils;


    @Override
    public void persistUser(User user) {
        userDao.persistUser(user);
    }

    @Override
    public void createUser(User user) throws com.nick.pm.utils.mail.MailingException {
        org.springframework.security.crypto.password.PasswordEncoder encoder
                = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();


//        StandardPasswordEncoder encoder = new StandardPasswordEncoder("12345");
        user.setPassword(encoder.encode(user.getPassword()));
        long userCreatedId = userDao.createUser(user);
        mailing.sendMailWithConfirmationLink(user.getEmail(),encoder.encode(user.getEmail()),userCreatedId);
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


}
