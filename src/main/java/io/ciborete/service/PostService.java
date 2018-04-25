package io.ciborete.service;


import io.ciborete.dto.Request;
import io.ciborete.model.Post;
import io.ciborete.model.Post;

import java.util.List;

public interface PostService {
    Post addPost(Post post);
    void deletePost(String PostId);
    void deletePosts(List<String> posts);
    List<Post> findPosts();
    List<Post> findPosts(Request request);
    Post findPost(String postId);
    List<Post> findPostsByIds(List<String> postIds);
    Post updatePost(String postId,Post post);
}
