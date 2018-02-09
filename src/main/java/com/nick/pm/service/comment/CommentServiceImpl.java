package com.nick.pm.service.comment;

import com.nick.pm.dao.comment.CommentDAO;
import com.nick.pm.entity.Comment;
import com.nick.pm.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentDAO commentDAO;

    @Override
    public Comment getCommentById(Long id) {
        return commentDAO.getCommentById(id);
    }

    @Override
    public void saveComment(Comment comment) {
        commentDAO.saveComment(comment);
    }

    @Override
    public void deleteComment(Long id) {
        Comment comment = commentDAO.getCommentById(id);
        commentDAO.deleteComment(comment);
    }

    @Override
    public List<Comment> getAllCommentsByTaskId(Task task) {
        return commentDAO.getAllCommentsByTaskId(task);
    }

    @Override
    public void updateComment(Comment comment, Long commentId) {
        Comment commentDB = commentDAO.getCommentById(commentId);
        commentDB.setComment(comment.getComment());
        commentDAO.updateComment(commentDB);
    }
}
