package com.nick.pm.dao.comment;


import com.nick.pm.entity.Comment;
import com.nick.pm.entity.Task;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Repository("commentDAOImpl")
@Transactional
public class CommentDAOImpl implements CommentDAO {

    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(this.getClass());

//    @Autowired
//    private MessageSource messageSource;

    @Resource(name = "sessionFactory")
    public SessionFactory sessionFactory;


    @Override
    public Comment getCommentById(Long id) {
        Comment comment= sessionFactory.getCurrentSession().get(Comment.class, id);
        LOGGER.info("Get comment by id {}", id);
        return comment;
    }

    @Override
    public Comment saveComment(Comment comment) {
        Long id = (Long) sessionFactory.getCurrentSession().save(comment);
        Comment commentById = getCommentById(id);
        LOGGER.info("Save new comment by {}", comment);
        return commentById;
    }

    @Override
    public void deleteComment(Comment comment) {
        sessionFactory.getCurrentSession().delete(comment);
        LOGGER.info("Delete comment {}", comment);
    }

    @Override
    public void updateComment(Comment comment) {
        sessionFactory.getCurrentSession().update(comment);
        LOGGER.info("Update comment by {}", comment);
    }

    @Override
    public List<Comment> getAllCommentsByTaskId(Long taskId) {
        Task task = sessionFactory.getCurrentSession().get(Task.class, taskId);
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Comment.class)
                .add(Restrictions.eq("taskId", task));
        criteria.addOrder(Order.asc("comment_date"));

        List<Comment> comments = criteria.list();
        LOGGER.info("Get all comments by task id {}", taskId);
        return comments;
    }
}
