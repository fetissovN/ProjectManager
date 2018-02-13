package com.nick.pm.converter;

import com.nick.pm.DTO.CommentDTO;
import com.nick.pm.entity.Comment;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class SpringConverterCommentDTOToComment implements Converter<CommentDTO, Comment> {

    @Override
    public Comment convert(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setComment(commentDTO.getComment());
        comment.setComment_date(commentDTO.getComment_date());
        return comment;
    }
}
