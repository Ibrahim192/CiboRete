package io.ciborete.service;

import io.ciborete.dto.Request;
import io.ciborete.model.Review;

import java.util.List;

public interface ReviewService {
    Review addReview(Review review);
    void deleteReview(String ReviewId);
    void deleteReviews(List<String> reviews);
    List<Review> findReviews();
    List<Review> findReviews(Request request);
    Review findReview(String reviewId);
    List<Review> findReviewsByIds(List<String> reviewIds);
    Review updateReview(String reviewId,Review review);
}
