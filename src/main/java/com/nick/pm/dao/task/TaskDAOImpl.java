package com.nick.pm.dao.task;

import com.nick.pm.entity.Project;
import com.nick.pm.entity.Task;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Locale;


@Repository("taskDAOImpl")
@Transactional
public class TaskDAOImpl implements TaskDAO{

    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "sessionFactory")
    public SessionFactory sessionFactory;

    @Override
    public void createTask(Task task) {
        sessionFactory.getCurrentSession().save(task);
        LOGGER.info("Create new task {} ", task);
    }

    @Override
    public List<Task> getAllTasks() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Project.class);
        criteria.addOrder(Order.desc("taskDate"));
        List result = criteria.list();
        LOGGER.info("Get all tasks ");
        return result;
    }

    @Override
    public Task getTaskById(Long id) {
        Task post = sessionFactory.getCurrentSession().get(Task.class, id);
        LOGGER.info("Get task by id {} ", id);
        return post;
    }

    @Override
    public void updateTask(Task task) {
        sessionFactory.getCurrentSession().update(task);
        LOGGER.info("Update task {} ", task);
    }

    @Override
    public void deleteTask(Task task) {
        sessionFactory.getCurrentSession().delete(task);
        LOGGER.info("Delete task {} ", task);
    }


}
