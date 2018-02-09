package com.nick.pm.dao.user;

import com.nick.pm.entity.User;
import org.hibernate.SessionFactory;
//import org.hibernate.query.Query;
import org.hibernate.Query;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Locale;

@Repository("userDaoImpl")
@Transactional
public class UserDaoImpl implements UserDAO {

//    private final Logger LOGGER = Logger.getLogger(getClass());
    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MessageSource messageSource;

    @Resource(name = "sessionFactory")
    public SessionFactory sessionFactory;


    @Override
    public Long createUser(User user) {
        Long userCreatedId = (Long) sessionFactory.getCurrentSession().save(user);
        LOGGER.info("New user saved {}", user);
//        LOGGER.info(messageSource.getMessage("log.new.user", new Object[] {user}, Locale.ENGLISH));
        return userCreatedId;
    }

    @Override
    public void persistUser(User user){
        sessionFactory.getCurrentSession().persist(user);
        LOGGER.info(messageSource.getMessage("log.new.user", new Object[] {user}, Locale.ENGLISH));
    }

    @Override
    public User getUserByEmail(String email) {

        Query query = sessionFactory.getCurrentSession().createQuery("FROM User WHERE email = :email");
        query.setParameter("email", email);

        List userEntities = query.list();
        if (userEntities.size()==0){
            return null;
        }
        User user = (User) userEntities.get(0);
        LOGGER.info("Get user by email {}", email);
//        LOGGER.info(messageSource.getMessage("log.get.user.email", new Object[] {email}, Locale.ENGLISH));
        return user;
    }

    @Override
    public void updateUser(User user) {

        sessionFactory.getCurrentSession().update(user);
        LOGGER.info(messageSource.getMessage("log.update.user", new Object[] {user}, Locale.ENGLISH));
    }

    @Override
    public User getUserById(Long id) {
        User user = sessionFactory.getCurrentSession().get(User.class, id);
        LOGGER.info(messageSource.getMessage("log.get.userById", new Object[] {id}, Locale.ENGLISH));
        return user;
    }

    @Override
    public void deleteUser(User user) {
        sessionFactory.getCurrentSession().delete(user);
        LOGGER.info(messageSource.getMessage("log.deleteUser", new Object[] {user}, Locale.ENGLISH));
    }

}
