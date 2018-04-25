package io.ciborete.controllers;

import io.ciborete.dto.Request;
import io.ciborete.model.Reply;
import io.ciborete.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/reply")
public class ReplyController {
    @Autowired
    ReplyService replyService;

    @RequestMapping(path="/add",method = {RequestMethod.POST})
    public ResponseEntity<?> addReply(@RequestBody Reply reply) {
        Reply createdReply = replyService.addReply(reply);
        return (createdReply != null) ?
                (new ResponseEntity<Reply>(createdReply, HttpStatus.CREATED)) :
                (new ResponseEntity<Reply>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(method = {RequestMethod.GET})
    public List<Reply> findReplies() {
        return replyService.findReplies();
    }

    @RequestMapping(path = "/{replyId}", method = RequestMethod.GET)
    public ResponseEntity<Reply> findReply(@PathVariable String replyId) {
        Reply reply = replyService.findReply(replyId);
        return (reply != null) ?
                new ResponseEntity<Reply>(reply, HttpStatus.OK) :
                new ResponseEntity<Reply>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path ="/ids",method = RequestMethod.POST)
    public List<Reply> findReplyByIds(@RequestBody List<String> replyIds) {
        return replyService.findRepliesByIds(replyIds);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<List<Reply>> findReplies(@RequestBody Request request) {
        System.out.println(request.toString());
        if(request==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(replyService.findReplies(request),HttpStatus.OK);
    }

    @RequestMapping(path = "/{replyId}", method = RequestMethod.PUT)
    public Reply updateReply(@PathVariable String replyId, @RequestBody Reply reply) {
        return replyService.updateReply(replyId, reply);
    }

    @RequestMapping(path = "/{replyId}", method = RequestMethod.DELETE)
    public void deleteReply(@PathVariable String replyId) {
        replyService.deleteReply(replyId);
    }
}
