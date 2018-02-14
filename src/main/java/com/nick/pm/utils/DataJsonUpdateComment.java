package com.nick.pm.utils;

/**
 * Object for json
 * For updating comment
 */
public class DataJsonUpdateComment {

    private long commentId;

    private String text;

    public DataJsonUpdateComment() {
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
