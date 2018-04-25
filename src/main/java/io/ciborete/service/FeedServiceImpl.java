package io.ciborete.service;

import io.ciborete.dto.Request;
import io.ciborete.model.Review;
import io.ciborete.model.WallPost;

import java.util.List;

public class FeedServiceImpl implements FeedService {

    @Override
    public List<WallPost> fetchFeeds(String userId, Request request) {
        return null;
    }

    @Override
    public List<WallPost> fetchFeeds(String userId) {
        return null;
    }

    @Override
    public List<Review> fetchReviews(String userId, Request request) {
        return null;
    }

    @Override
    public List<Review> fetchReviews(String userId) {
        return null;
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
