package com.nick.pm.dao.comment;


import com.nick.pm.entity.Comment;
import com.nick.pm.entity.Task;
import com.nick.pm.entity.Project;

import java.util.List;

public interface CommentDAO {

    Comment getCommentById(Long id);

    void saveComment(Comment comment);

    void deleteComment(Comment comment);

    void updateComment(Comment comment);

    List<Comment> getAllCommentsByTaskId(Task task);


}
