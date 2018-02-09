package com.nick.pm.service.comment;


import com.nick.pm.entity.Comment;
import com.nick.pm.entity.Task;

import java.util.List;

public interface CommentService {

    Comment getCommentById(Long id);

    void saveComment(Comment comment);

    void deleteComment(Long id);

    List<Comment> getAllCommentsByTaskId(Task task);

    void updateComment(Comment comment, Long messageId);
}
