package io.ciborete.service;

import io.ciborete.dto.Request;
import io.ciborete.exceptions.AssetNotFoundException;
import io.ciborete.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    MongoOperations mongoOperations;

    @Override
    public Comment addComment(Comment comment) {
        comment.setCommentId(UUID.randomUUID().toString());
        comment.setCreatedTime(new Date());
        comment.setModifiedTime(new Date());
        mongoOperations.save(comment,"comments");
        return comment;
    }

    @Override
    public void deleteComment(String commentId) {
        Comment comment = mongoOperations.findById(commentId,Comment.class,"comments");
        if(comment!=null){
            mongoOperations.remove(new Query(Criteria.where("commentId").is(commentId)));
        }
        throw new AssetNotFoundException("Comment not found with comment Id "+commentId);
    }

    @Override
    public List<Comment> findComments() {
        return mongoOperations.findAll(Comment.class,"comments");
    }

    @Override
    public List<Comment> findComments(Request request) {
        Query query = new Query();
        query.with(new PageRequest(request.getPageOffset(),request.getPageLimit()));
        if(request.getSortKey()==null || request.getSortKey().isEmpty()){
            request.setSortKey("createdTime");
        }
        query.with(new Sort(new Sort.Order(Sort.Direction.valueOf(request.getSortOrder().name()),request.getSortKey())));
        return mongoOperations.find(query,Comment.class,"comments");
    }

    @Override
    public Comment findComment(String commentId) {
        return mongoOperations.findById(commentId,Comment.class,"comments");
    }

    @Override
    public List<Comment> findCommentsByIds(List<String> commentIds) {
        return mongoOperations.find(Query.query(Criteria.where("commentId").in(commentIds)),Comment.class,"comments");
    }

    @Override
    public Comment updateComment(String wallPostId, Comment comment) {
        Comment currentComment = mongoOperations.findById(comment.getCommentId(),Comment.class,"comments");
        if(currentComment!=null){
            comment.setModifiedTime(new Date());
            mongoOperations.save(comment,"comments");
        }
        throw new AssetNotFoundException("Comment not found with comment Id "+comment.getCommentId());
    }

    @Override
    public void deleteComments(List<String> comments){
        mongoOperations.remove(Query.query(Criteria.where("commentId").in(comments)),Comment.class,"comments");
    }
}
