package io.ciborete.service;

import io.ciborete.dto.Request;
import io.ciborete.exceptions.AssetNotFoundException;
import io.ciborete.model.Friends;
import io.ciborete.model.Restaurant;
import io.ciborete.model.Review;
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
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    MongoOperations mongoOperations;

    @Autowired
    RestaurantService restaurantService;

    @Override
    public Review addReview(Review review) {
        review.setReviewId(UUID.randomUUID().toString());
        review.setCreatedTime(new Date());
        review.setModifiedTime(new Date());
        mongoOperations.save(review);
        return review;
    }

    @Override
    public void deleteReview(String reviewId) {
        Review review = mongoOperations.findById(reviewId,Review.class);
        if(review!=null){
            mongoOperations.remove(new Query(Criteria.where("reviewId").is(reviewId)));
        }
        else {
            throw new AssetNotFoundException("Review not found with review Id " + reviewId);
        }
    }

    @Override
    public List<Review> findReviews() {
        return mongoOperations.findAll(Review.class);
    }

    @Override
    public List<Review> findReviews(String userId, String loggedInUserId, Request request) {
        Friends getFriends = mongoOperations.findOne(Query.query(Criteria.where("userId").is(userId)),Friends.class);
        if(getFriends==null || !getFriends.getFriends().keySet().contains(loggedInUserId)){
            return Collections.emptyList();
        }
        Query query = new Query(Criteria.where("userId").is(userId)).addCriteria(Criteria.where("anonymous").is(false));
        query.with(new PageRequest(request.getPageOffset(),request.getPageLimit()));
        if(request.getSortKey()==null || request.getSortKey().isEmpty()){
            request.setSortKey("createdTime");
        }
        query.with(new Sort(new Sort.Order(Sort.Direction.valueOf(request.getSortOrder().name()),request.getSortKey())));
        return mongoOperations.find(query,Review.class);
    }

    @Override
    public Review findReview(String reviewId) {
        return mongoOperations.findById(reviewId,Review.class);
    }

    @Override
    public List<Review> findReviewsByIds(List<String> reviewIds) {
        return mongoOperations.find(Query.query(Criteria.where("reviewId").in(reviewIds)),Review.class);
    }

    @Override
    public Review updateReview(String wallReviewId, Review review) {
        Review currentReview = mongoOperations.findById(review.getReviewId(),Review.class);
        if(currentReview!=null){
            review.setModifiedTime(new Date());
            mongoOperations.save(review);
        }
        else {
            throw new AssetNotFoundException("Review not found with review Id " + review.getReviewId());
        }
        return review;
    }

    @Override
    public void deleteReviews(List<String> reviews){
        mongoOperations.remove(Query.query(Criteria.where("reviewId").in(reviews)),Review.class);
    }

    @Override
    public List<Review> findOwnReviews(String loggedInUserId, Request request) {
        Query query = new Query(Criteria.where("userId").is(loggedInUserId));
        query.with(new PageRequest(request.getPageOffset(),request.getPageLimit()));
        if(request.getSortKey()==null || request.getSortKey().isEmpty()){
            request.setSortKey("createdTime");
        }
        query.with(new Sort(new Sort.Order(Sort.Direction.valueOf(request.getSortOrder().name()),request.getSortKey())));
        return mongoOperations.find(query,Review.class);
    }

    @Override
    public List<Review> fetchMentionedReviews(String userId, Request request) {
        Query restaurantQuery = new Query(Criteria.where("ownerId").is(userId));
        Restaurant restaurant = mongoOperations.findOne(restaurantQuery,Restaurant.class);
        if(restaurant==null){
            return Collections.emptyList();
        }
        Query query = new Query(Criteria.where("restaurantId").is(restaurant.getRestaurantId()));
        query.with(new PageRequest(request.getPageOffset(),request.getPageLimit()));
        if(request.getSortKey()==null || request.getSortKey().isEmpty()){
            request.setSortKey("createdTime");
        }
        query.with(new Sort(new Sort.Order(Sort.Direction.valueOf(request.getSortOrder().name()),request.getSortKey())));
        return mongoOperations.find(query,Review.class);

    }
}
