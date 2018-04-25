package io.ciborete.adapters;

import io.ciborete.enums.PostType;
import io.ciborete.model.Post;
import io.ciborete.model.Review;
import io.ciborete.model.WallPost;

import java.util.UUID;

public class PostAdapter {

    public static WallPost adaptToWallPost(Post post){
        boolean brandMention = post.getRestaurantId()!=null && !post.getRestaurantId().isEmpty() ;
        WallPost wallPost = new WallPost();
        wallPost.setPostId(post.getPostId());
        wallPost.setAssets(post.getItems());
        wallPost.setBrandMention(brandMention);
        wallPost.setCaption(post.getCaption());
        wallPost.setUserId(post.getUserId());
        wallPost.setPostType(post.getPostingRestaurantId()!=null? PostType.RESTAURANT_POST:PostType.USER_POST);
        wallPost.setTags(post.getTags());
        wallPost.setUpvotes(0l);
        return wallPost;
    }

    public static Review adaptToReview(Post post) {
        if(post.getRestaurantId()==null || post.getRestaurantId().isEmpty()){
            return null;
        }
        Review review = new Review();
        review.setUserId(post.getUserId());
        review.setReviewId(UUID.randomUUID().toString());
        review.setPostId(post.getPostId());
        review.setRestaurantId(post.getRestaurantId());
        review.setTags(post.getTags());
        if(post.isSeparateReview()){
            review.setCaption(post.getReviewCaption());
            review.setItems(post.getReviewItems());
        }
        else {
            review.setCaption(post.getCaption());
            review.setItems(post.getItems());
        }
        review.setAnonymous(post.isAnonymous());
        review.setRating(0);
        return review;

    }
}
