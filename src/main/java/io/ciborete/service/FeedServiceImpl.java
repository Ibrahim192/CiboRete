package io.ciborete.service;

import io.ciborete.dto.Request;
import io.ciborete.model.Friends;
import io.ciborete.model.Review;
import io.ciborete.model.WallPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class FeedServiceImpl implements FeedService {

    @Autowired
    MongoOperations mongoOperations;

    @Override
    public List<WallPost> fetchFeeds(String userId, Request request) {
        Friends response = mongoOperations.findOne(Query.query(Criteria.where("userId").is(userId)),Friends.class);
        if(response==null || response.getFriends().keySet().isEmpty()){
            return Collections.emptyList();
        }
        Query query = new Query(Criteria.where("userId").in(response.getFriends().keySet()));
        query.with(new PageRequest(request.getPageOffset(),request.getPageLimit()));
        if(request.getSortKey()==null || request.getSortKey().isEmpty()){
            request.setSortKey("createdTime");
        }
        query.with(new Sort(new Sort.Order(Sort.Direction.valueOf(request.getSortOrder().name()),request.getSortKey())));
        return mongoOperations.find(query,WallPost.class);
    }

    @Override
    public List<WallPost> fetchFeeds(String userId) {
        Friends response = mongoOperations.findOne(Query.query(Criteria.where("userId").is(userId)),Friends.class);
        if(response==null || response.getFriends().keySet().isEmpty()){
            return Collections.emptyList();
        }
        return mongoOperations.find(Query.query(Criteria.where("userId").in(response.getFriends().keySet())),WallPost.class);

    }

    @Override
    public List<Review> fetchReviews(String userId, Request request) {
        Friends response = mongoOperations.findOne(Query.query(Criteria.where("userId").is(userId))
                .addCriteria(Criteria.where("anonymous").is(false)),Friends.class);
        if(response==null || response.getFriends().keySet().isEmpty()){
            return Collections.emptyList();
        }
        Query query = new Query(Criteria.where("userId").in(response.getFriends().keySet()));
        query.with(new PageRequest(request.getPageOffset(),request.getPageLimit()));
        if(request.getSortKey()==null || request.getSortKey().isEmpty()){
            request.setSortKey("createdTime");
        }
        query.with(new Sort(new Sort.Order(Sort.Direction.valueOf(request.getSortOrder().name()),request.getSortKey())));
        return mongoOperations.find(query,Review.class);
    }

    @Override
    public List<Review> fetchReviews(String userId) {
        Friends response = mongoOperations.findOne(Query.query(Criteria.where("userId").is(userId))
                .addCriteria(Criteria.where("anonymous").is(false)),Friends.class);
        if(response==null || response.getFriends().keySet().isEmpty()){
            return Collections.emptyList();
        }
        return mongoOperations.find(Query.query(Criteria.where("userId").in(response.getFriends().keySet())),Review.class);

    }

    @Override
    public List<WallPost> fetchRestaurantPosts(String restaurantId) {
        return null;
    }

    @Override
    public List<WallPost> fetchRestaurantPosts(String restaurantId, Request request) {
        return null;
    }

    @Override
    public List<Review> fetchRestaurantReviews(String restaurantId) {
        return null;
    }

    @Override
    public List<Review> fetchRestaurantReviews(String restaurantId, Request request) {
        return null;
    }
}
