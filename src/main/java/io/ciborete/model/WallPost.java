package io.ciborete.model;

import io.ciborete.enums.PostType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
@CompoundIndexes({
        @CompoundIndex(name="rest_user_post",def = "{'restaurantId':1,'userId':1,'postId':1}")
})
public class WallPost {

    List<String> tags;

    List<String> items;

    boolean brandMention;

    String restaurantId;

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getAssets() {
        return items;
    }

    public void setAssets(List<String> items) {
        this.items = items;
    }

    public boolean isBrandMention() {
        return brandMention;
    }

    public void setBrandMention(boolean brandMention) {
        this.brandMention = brandMention;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public PostType getPostType() {
        return postType;
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

    public void setPostType(PostType postType) {
        this.postType = postType;
    }

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getWallPostId() {
        return wallPostId;
    }

    public void setWallPostId(String wallPostId) {
        this.wallPostId = wallPostId;
    }

    public Long getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(Long upvotes) {
        this.upvotes = upvotes;
    }

    PostType postType;

    Date createdTime;

    String userId;

    String postId;

    @Id
    @Indexed
    String wallPostId;

    Long upvotes;

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    String caption;

    Date modifiedTime;

    boolean deleted;


}
