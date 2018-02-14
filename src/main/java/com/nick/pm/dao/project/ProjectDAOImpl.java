package com.nick.pm.dao.project;

import com.nick.pm.entity.Project;
import com.nick.pm.entity.Task;
import com.nick.pm.entity.User;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Repository("projectDAOImpl")
@Transactional
public class ProjectDAOImpl implements ProjectDAO {

    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(this.getClass());

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
        LOGGER.info("Get all projects ");
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
        Project project = sessionFactory.getCurrentSession().get(Project.class, id);
        LOGGER.info("Get project by id {} ", id);
        return project;
    }

    @Override
    public void updateProject(Project project) {
        sessionFactory.getCurrentSession().update(project);
        LOGGER.info("Update project {} ", project);
    }

    @Override
    public void deleteProject(Project project) {
        sessionFactory.getCurrentSession().delete(project);
        LOGGER.info("Delete project {} ", project);
    }

    @Override
    public List<Task> getTasksOfProject(Project project) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Task.class)
                .add(Restrictions.eq("projectId", project));
        criteria.addOrder(Order.desc("taskDate"));
        List result = criteria.list();
        LOGGER.info("Get tasks of project {} ", project);
        return result;
    }

}
