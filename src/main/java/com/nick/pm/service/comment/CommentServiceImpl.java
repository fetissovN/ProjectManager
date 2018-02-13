package com.nick.pm.service.comment;

import com.nick.pm.DTO.CommentDTO;
import com.nick.pm.converter.SpringConverterCommentDTOToComment;
import com.nick.pm.converter.SpringConverterCommentToCommentDTO;
import com.nick.pm.dao.comment.CommentDAO;
import com.nick.pm.dao.task.TaskDAO;
import com.nick.pm.dao.user.UserDAO;
import com.nick.pm.entity.Comment;
import com.nick.pm.entity.Task;
import com.nick.pm.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private TaskDAO taskDAO;

    @Autowired
    private SpringConverterCommentToCommentDTO springConverterCommentToCommentDTO;

    @Autowired
    private SpringConverterCommentDTOToComment springConverterCommentDTOToComment;

    @Override
    public Comment getCommentById(Long id) {
        return commentDAO.getCommentById(id);
    }

    @Override
    public CommentDTO saveComment(CommentDTO commentDTO) {
        Comment comment = springConverterCommentDTOToComment.convert(commentDTO);
        User user = userDAO.getUserById(commentDTO.getUserId());
        Task task = taskDAO.getTaskById(commentDTO.getTaskId());
        comment.setUserId(user);
        comment.setTaskId(task);
        Comment savedComment = commentDAO.saveComment(comment);
        return springConverterCommentToCommentDTO.convert(savedComment);
    }

    @Override
    public void deleteComment(Long id) {
        Comment comment = commentDAO.getCommentById(id);
        commentDAO.deleteComment(comment);
    }

    @Override
    public List<Comment> getAllCommentsByTaskId(Task task) {
        return commentDAO.getAllCommentsByTaskId(task.getId());
    }

    @Override
    public void updateComment(String text, Long commentId) {
        Comment commentDB = commentDAO.getCommentById(commentId);
        commentDB.setComment(text);
        commentDAO.updateComment(commentDB);
    }
}
