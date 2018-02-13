package com.nick.pm.dao.comment;


import com.nick.pm.entity.Comment;

import java.util.List;

public interface CommentDAO {

    Comment getCommentById(Long id);

    Comment saveComment(Comment comment);

    void deleteComment(Comment comment);

    void updateComment(Comment comment);

    List<Comment> getAllCommentsByTaskId(Long taskId);


}
