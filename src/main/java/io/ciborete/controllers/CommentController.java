package io.ciborete.controllers;

import io.ciborete.dto.Request;
import io.ciborete.model.Comment;
import io.ciborete.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/comments")
public class CommentController {
    @Autowired
    CommentService commentService;

    @RequestMapping(path="/add",method = {RequestMethod.POST})
    public ResponseEntity<?> addComment(@RequestBody Comment comment) {
        Comment createdComment = commentService.addComment(comment);
        return (createdComment != null) ?
                (new ResponseEntity<Comment>(createdComment, HttpStatus.CREATED)) :
                (new ResponseEntity<Comment>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(method = {RequestMethod.GET})
    public List<Comment> findComments() {
        return commentService.findComments();
    }

    @RequestMapping(path = "/{commentId}", method = RequestMethod.GET)
    public ResponseEntity<Comment> findComment(@PathVariable String commentId) {
        Comment comment = commentService.findComment(commentId);
        return (comment != null) ?
                new ResponseEntity<Comment>(comment, HttpStatus.OK) :
                new ResponseEntity<Comment>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path ="/ids",method = RequestMethod.POST)
    public List<Comment> findCommentByIds(@RequestBody List<String> commentIds) {
        return commentService.findCommentsByIds(commentIds);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<List<Comment>> findComments(@RequestBody Request request) {
        System.out.println(request.toString());
        if(request==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(commentService.findComments(request),HttpStatus.OK);
    }

    @RequestMapping(path = "/{commentId}", method = RequestMethod.PUT)
    public Comment updateComment(@PathVariable String commentId, @RequestBody Comment comment) {
        return commentService.updateComment(commentId, comment);
    }

    @RequestMapping(path = "/{commentId}", method = RequestMethod.DELETE)
    public void deleteComment(@PathVariable String commentId) {
        commentService.deleteComment(commentId);
    }
}
