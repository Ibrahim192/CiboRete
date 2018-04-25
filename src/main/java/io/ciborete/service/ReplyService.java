package io.ciborete.service;

import io.ciborete.dto.Request;
import io.ciborete.model.Reply;

import java.util.List;

public interface ReplyService {

    Reply addReply(Reply reply);
    void deleteReply(String ReplyId);
    void deleteReplies(List<String> replys);
    List<Reply> findReplies();
    List<Reply> findReplies(Request request);
    Reply findReply(String replyId);
    List<Reply> findRepliesByIds(List<String> replyIds);
    Reply updateReply(String replyId,Reply reply);
}
