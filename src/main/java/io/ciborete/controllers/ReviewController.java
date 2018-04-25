package io.ciborete.controllers;

import io.ciborete.dto.Request;
import io.ciborete.model.Review;
import io.ciborete.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;
    @RequestMapping(path="/add",method = {RequestMethod.POST})
    public ResponseEntity<?> addReview(@RequestBody Review review) {
        Review createdReview = reviewService.addReview(review);
        return (createdReview != null) ?
                (new ResponseEntity<Review>(createdReview, HttpStatus.CREATED)) :
                (new ResponseEntity<Review>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(method = {RequestMethod.GET})
    public List<Review> findReviews() {
        return reviewService.findReviews();
    }

    @RequestMapping(path = "/{reviewId}", method = RequestMethod.GET)
    public ResponseEntity<Review> findReview(@PathVariable String reviewId) {
        Review review = reviewService.findReview(reviewId);
        return (review != null) ?
                new ResponseEntity<Review>(review, HttpStatus.OK) :
                new ResponseEntity<Review>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path ="/ids",method = RequestMethod.POST)
    public List<Review> findReviewByIds(@RequestBody List<String> reviewIds) {
        return reviewService.findReviewsByIds(reviewIds);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<List<Review>> findReviews(@RequestBody Request request) {
        System.out.println(request.toString());
        if(request==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(reviewService.findReviews(request),HttpStatus.OK);
    }

    @RequestMapping(path = "/{reviewId}", method = RequestMethod.PUT)
    public Review updateReview(@PathVariable String reviewId, @RequestBody Review review) {
        return reviewService.updateReview(reviewId, review);
    }

    @RequestMapping(path = "/{reviewId}", method = RequestMethod.DELETE)
    public void deleteReview(@PathVariable String reviewId) {
        reviewService.deleteReview(reviewId);
    }
}
