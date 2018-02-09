package com.nick.pm.dao.comment;


import com.nick.pm.entity.Comment;
import com.nick.pm.entity.Task;
import com.nick.pm.entity.Project;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Locale;

@Repository("commentDAOImpl")
@Transactional
public class CommentDAOImpl implements CommentDAO {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Resource(name = "sessionFactory")
    public SessionFactory sessionFactory;


    @Override
    public Comment getCommentById(Long id) {
        Comment comment= sessionFactory.getCurrentSession().get(Comment.class, id);
        LOGGER.info(messageSource.getMessage("log.get.comment", new Object[] {comment}, Locale.ENGLISH));
        return comment;
    }

    @Override
    public void saveComment(Comment comment) {
        sessionFactory.getCurrentSession().save(comment);
        LOGGER.info(messageSource.getMessage("log.new.comment", new Object[] {comment}, Locale.ENGLISH));
    }

    @Override
    public void deleteComment(Comment comment) {
        sessionFactory.getCurrentSession().delete(comment);
        LOGGER.info(messageSource.getMessage("log.delete.comment", new Object[] {comment}, Locale.ENGLISH));
    }

    @Override
    public void updateComment(Comment comment) {
        sessionFactory.getCurrentSession().update(comment);
        LOGGER.info(messageSource.getMessage("log.update.comment", new Object[] {comment}, Locale.ENGLISH));

    }

    @Override
    public List<Comment> getAllCommentsByTaskId(Task task) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Comment.class)
                .add(Restrictions.eq("task_id", task));
        criteria.addOrder(Order.desc("comment_date"));

        List<Comment> comments = criteria.list();
        LOGGER.info(messageSource.getMessage("log.get.comments", new Object[] {comments}, Locale.ENGLISH));
        return comments;
    }
}
