package io.ciborete.service;

import io.ciborete.dto.Request;
import io.ciborete.exceptions.AssetNotFoundException;
import io.ciborete.model.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    MongoOperations mongoOperations;

    @Override
    public Reply addReply(Reply reply) {
        reply.setReplyId(UUID.randomUUID().toString());
        reply.setCreatedTime(new Date());
        reply.setModifiedTime(new Date());
        mongoOperations.save(reply,"reply");
        return reply;
    }

    @Override
    public void deleteReply(String replyId) {
        Reply reply = mongoOperations.findById(replyId,Reply.class,"reply");
        if(reply!=null){
            mongoOperations.remove(new Query(Criteria.where("replyId").is(replyId)));
        }
        throw new AssetNotFoundException("Reply not found with reply Id "+replyId);
    }

    @Override
    public List<Reply> findReplies() {
        return mongoOperations.findAll(Reply.class,"reply");
    }

    @Override
    public List<Reply> findReplies(Request request) {
        Query query = new Query();
        query.with(new PageRequest(request.getPageOffset(),request.getPageLimit()));
        if(request.getSortKey()==null || request.getSortKey().isEmpty()){
            request.setSortKey("createdTime");
        }
        query.with(new Sort(new Sort.Order(Sort.Direction.valueOf(request.getSortOrder().name()),request.getSortKey())));
        return mongoOperations.find(query,Reply.class,"reply");
    }

    @Override
    public Reply findReply(String replyId) {
        return mongoOperations.findById(replyId,Reply.class,"reply");
    }

    @Override
    public List<Reply> findRepliesByIds(List<String> replyIds) {
        return mongoOperations.find(Query.query(Criteria.where("replyId").in(replyIds)),Reply.class,"reply");
    }

    @Override
    public Reply updateReply(String wallPostId, Reply reply) {
        Reply currentReply = mongoOperations.findById(reply.getReplyId(),Reply.class,"reply");
        if(currentReply!=null){
            reply.setModifiedTime(new Date());
            mongoOperations.save(reply,"reply");
        }
        throw new AssetNotFoundException("Reply not found with reply Id "+reply.getReplyId());
    }

    @Override
    public void deleteReplies(List<String> replys){
        mongoOperations.remove(Query.query(Criteria.where("replyId").in(replys)),Reply.class,"reply");
    }

}
