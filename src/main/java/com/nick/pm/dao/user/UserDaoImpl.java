package com.nick.pm.dao.user;

import com.nick.pm.entity.Project;
import com.nick.pm.entity.Role;
import com.nick.pm.entity.User;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
//import org.hibernate.query.Query;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
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
        return userCreatedId;
    }

    @Override
    public void persistUser(User user){
        sessionFactory.getCurrentSession().persist(user);
        LOGGER.info("User {} persisted", user);
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
        return user;
    }

    @Override
    public void updateUserDevelopers(User user, Project project) {
        sessionFactory.getCurrentSession().update(user);
        sessionFactory.getCurrentSession().update(project);
        LOGGER.info("User {} updated", user);
    }

    @Override
    public void updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
        LOGGER.info("User {} updated", user);
    }

    @Override
    public User getUserById(Long id) {
        User user = sessionFactory.getCurrentSession().get(User.class, id);
        LOGGER.info("Get user by id {}", id);
        return user;
    }

    @Override
    public void deleteUser(User user) {
        sessionFactory.getCurrentSession().delete(user);
        LOGGER.info(messageSource.getMessage("log.deleteUser", new Object[] {user}, Locale.ENGLISH));
    }

    @Override
    public List<Project> getListProjectsCreated(User user) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Project.class)
                .add(Restrictions.eq("userId", user));
        criteria.addOrder(Order.desc("projectDate"));
        List<Project> projects = criteria.list();
        LOGGER.info("Get all projects of user {}", user);
        return projects;
    }

    @Override
    public List<User> getListDevelopers() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class)
                .add(Restrictions.eq("role", Role.DEVELOPER));
        List<User> developers = criteria.list();
        LOGGER.info("Get all developers");
        return developers;
    }


}
