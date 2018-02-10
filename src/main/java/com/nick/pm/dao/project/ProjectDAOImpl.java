package com.nick.pm.dao.project;

import com.nick.pm.DTO.UserDTO;
import com.nick.pm.entity.Project;
import com.nick.pm.entity.User;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
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


@Repository("projectDAOImpl")
@Transactional
public class ProjectDAOImpl implements ProjectDAO {

    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MessageSource messageSource;

    @Resource(name = "sessionFactory")
    public SessionFactory sessionFactory;

    @Override
    public void createProject(Project project) {
        sessionFactory.getCurrentSession().save(project);
        LOGGER.info("Project created {}", project);

    }

    @Override
    public List<Project> getAllProjects() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Project.class);
        criteria.addOrder(Order.desc("postDate"));
        List result = criteria.list();
        LOGGER.info(messageSource.getMessage("log.get.projects", new Object[] {result}, Locale.ENGLISH));
        return result;
    }

    @Override
    public List<Project> getAllProjectsOfManager(User user) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Project.class)
                .add(Restrictions.eq("userId", user));
        criteria.addOrder(Order.desc("postDate"));
        List result = criteria.list();
        LOGGER.info("Get manager {} projects ", user);
        return result;
    }

    @Override
    public Project getProjectById(Long id) {
        Project post = sessionFactory.getCurrentSession().get(Project.class, id);
        LOGGER.info(messageSource.getMessage("log.get.project", new Object[] {id}, Locale.ENGLISH));
        return post;
    }

    @Override
    public void updateProject(Project post) {
        sessionFactory.getCurrentSession().update(post);
        LOGGER.info(messageSource.getMessage("log.update.project", new Object[] {post}, Locale.ENGLISH));
    }

    @Override
    public void deleteProject(Project post) {
        sessionFactory.getCurrentSession().delete(post);
        LOGGER.info(messageSource.getMessage("log.delete.project", new Object[] {post}, Locale.ENGLISH));
    }
}
