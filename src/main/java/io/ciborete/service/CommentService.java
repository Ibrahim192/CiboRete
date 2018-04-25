package io.ciborete.service;

import io.ciborete.dto.Request;
import io.ciborete.model.Comment;

import java.util.List;

public interface CommentService {

    Comment addComment(Comment comment);
    void deleteComment(String CommentId);
    void deleteComments(List<String> comments);
    List<Comment> findComments();
    List<Comment> findComments(Request request);
    Comment findComment(String commentId);
    List<Comment> findCommentsByIds(List<String> commentIds);
    Comment updateComment(String commentId,Comment comment);

}
