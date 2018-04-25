package io.ciborete.service;

import io.ciborete.dto.Request;
import io.ciborete.model.Review;
import io.ciborete.model.WallPost;

import java.util.List;

public interface FeedService {

    public List<WallPost> fetchFeeds(String userId,Request request);

    public List<WallPost> fetchFeeds(String userId);

    public List<Review> fetchReviews(String userId,Request request);

    public List<Review> fetchReviews(String userId);

    public List<WallPost> fetchRestaurantPosts(String restaurantId);

    public List<WallPost> fetchRestaurantPosts(String restaurantId,Request request);

    public List<Review> fetchRestaurantReviews(String restaurantId);

    public List<Review> fetchRestaurantReviews(String restaurantId,Request request);

}
