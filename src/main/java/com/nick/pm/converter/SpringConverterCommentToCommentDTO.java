package com.nick.pm.converter;

import com.nick.pm.DTO.CommentDTO;
import com.nick.pm.entity.Comment;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class SpringConverterCommentToCommentDTO implements Converter<Comment, CommentDTO> {

    @Override
    public CommentDTO convert(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setComment(comment.getComment());
        commentDTO.setComment_date(comment.getComment_date());
        return commentDTO;
    }
}
