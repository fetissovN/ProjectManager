package com.nick.pm.service.comment;


import com.nick.pm.DTO.CommentDTO;
import com.nick.pm.entity.Comment;
import com.nick.pm.entity.Task;

import java.util.List;

public interface CommentService {

    Comment getCommentById(Long id);

    CommentDTO saveComment(CommentDTO commentDTO);

    void deleteComment(Long id);

    List<Comment> getAllCommentsByTaskId(Task task);

    void updateComment(String text, Long messageId);
}
