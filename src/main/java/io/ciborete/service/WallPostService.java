package io.ciborete.service;

import io.ciborete.dto.Request;
import io.ciborete.model.WallPost;

import java.util.List;

public interface WallPostService {

    WallPost addWallPost(WallPost wallPost);
    void deleteWallPost(String WallPostId);
    void deleteWallPosts(List<String> wallPosts);
    List<WallPost> findWallPosts();
    List<WallPost> findWallPosts(String userId, String loggedInUser,Request request);

    List<WallPost> findOwnPosts(String loggedInUserId, Request request);

    WallPost findWallPost(String wallPostId);
    List<WallPost> findWallPostsByIds(List<String> wallPostIds);
    WallPost updateWallPost(String wallPostId,WallPost wallPost);
}
