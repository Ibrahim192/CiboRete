package io.ciborete.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Document(collection = "posts")
@CompoundIndexes({
        @CompoundIndex(name="user_restaurant",def="{'userId':1,'restaurantId':1}")
})
public class Post {

    List<String> items;

    Date createdTime;

    Date modifiedTime;

    boolean deleted;

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    boolean isAnonymous;

    public String getPostingRestaurantId() {
        return postingRestaurantId;
    }

    public void setPostingRestaurantId(String postingRestaurantId) {
        this.postingRestaurantId = postingRestaurantId;
    }

    String postingRestaurantId;

    String userId;

    String restaurantId;

    List<String> tags;

    @Indexed
    @Id
    String postId;

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    String caption;

    boolean separateReview;

    public boolean isSeparateReview() {
        return separateReview;
    }

    public void setSeparateReview(boolean separateReview) {
        this.separateReview = separateReview;
    }

    public String getReviewCaption() {
        return reviewCaption;
    }

    public void setReviewCaption(String reviewCaption) {
        this.reviewCaption = reviewCaption;
    }

    public List<String> getReviewItems() {
        return reviewItems;
    }

    public void setReviewItems(List<String> reviewItems) {
        this.reviewItems = reviewItems;
    }

    String reviewCaption=null;

    List<String> reviewItems=null;


}
