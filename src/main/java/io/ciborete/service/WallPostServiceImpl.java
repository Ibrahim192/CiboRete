package io.ciborete.service;

import io.ciborete.dto.Request;
import io.ciborete.exceptions.AssetNotFoundException;
import io.ciborete.model.Friends;
import io.ciborete.model.WallPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class WallPostServiceImpl implements WallPostService {
    @Autowired
    MongoOperations mongoOperations;

    @Override
    public WallPost addWallPost(WallPost wallPost) {
        wallPost.setWallPostId(UUID.randomUUID().toString());
        wallPost.setCreatedTime(new Date());
        wallPost.setModifiedTime(new Date());
        mongoOperations.save(wallPost);
        return wallPost;
    }

    @Override
    public void deleteWallPost(String wallPostId) {
        WallPost wallPost = mongoOperations.findById(wallPostId,WallPost.class,"wallposts");
        if(wallPost!=null){
            mongoOperations.remove(new Query(Criteria.where("wallPostId").is(wallPostId)));
        }
        else {
            throw new AssetNotFoundException("WallPost not found with wallPost Id " + wallPostId);
        }
    }

    @Override
    public List<WallPost> findWallPosts() {
        return mongoOperations.findAll(WallPost.class);
    }

    @Override
    public List<WallPost> findWallPosts(String userId, String loggedInUserId,Request request) {
        Friends getFriends = mongoOperations.findOne(Query.query(Criteria.where("userId").is(userId)),Friends.class);
        if(getFriends==null || !getFriends.getFriends().keySet().contains(loggedInUserId)){
            return Collections.emptyList();
        }
        Query query = new Query(Criteria.where("userId").is(userId));
        query.with(new PageRequest(request.getPageOffset(),request.getPageLimit()));
        if(request.getSortKey()==null || request.getSortKey().isEmpty()){
            request.setSortKey("createdTime");
        }
        query.with(new Sort(new Sort.Order(Sort.Direction.valueOf(request.getSortOrder().name()),request.getSortKey())));
        return mongoOperations.find(query,WallPost.class);
    }

    @Override
    public List<WallPost> findOwnPosts( String loggedInUserId,Request request) {
        Query query = new Query(Criteria.where("userId").is(loggedInUserId));
        query.with(new PageRequest(request.getPageOffset(),request.getPageLimit()));
        if(request.getSortKey()==null || request.getSortKey().isEmpty()){
            request.setSortKey("createdTime");
        }
        query.with(new Sort(new Sort.Order(Sort.Direction.valueOf(request.getSortOrder().name()),request.getSortKey())));
        return mongoOperations.find(query,WallPost.class);
    }



    @Override
    public WallPost findWallPost(String wallPostId) {
        return mongoOperations.findById(wallPostId,WallPost.class);
    }

    @Override
    public List<WallPost> findWallPostsByIds(List<String> wallPostIds) {
        return mongoOperations.find(Query.query(Criteria.where("wallPostId").in(wallPostIds)),WallPost.class);
    }

    @Override
    public WallPost updateWallPost(String wallPostId, WallPost wallPost) {
        WallPost currentWallPost = mongoOperations.findById(wallPost.getWallPostId(),WallPost.class);
        if(currentWallPost!=null){
            wallPost.setModifiedTime(new Date());
            mongoOperations.save(wallPost);
        }
        else {
            throw new AssetNotFoundException("WallPost not found with wallPost Id " + wallPostId);
        }
        return wallPost;
    }

    @Override
    public void deleteWallPosts(List<String> wallPosts){
        mongoOperations.remove(Query.query(Criteria.where("wallPostId").in(wallPosts)),WallPost.class);
    }
}
