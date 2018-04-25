package io.ciborete.service;


import io.ciborete.dto.Request;
import io.ciborete.exceptions.AssetNotFoundException;
import io.ciborete.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements  PostService {
    @Autowired
    MongoOperations mongoOperations;

    @Override
    public Post addPost(Post post) {
        post.setPostId(UUID.randomUUID().toString());
        post.setCreatedTime(new Date());
        post.setModifiedTime(new Date());
        mongoOperations.save(post,"post");
        return post;
    }

    @Override
    public void deletePost(String postId) {
        Post post = mongoOperations.findById(postId,Post.class,"post");
        if(post!=null){
            mongoOperations.remove(new Query(Criteria.where("postId").is(postId)));
        }
        throw new AssetNotFoundException("Post not found with post Id "+postId);
    }

    @Override
    public List<Post> findPosts() {
        return mongoOperations.findAll(Post.class,"post");
    }

    @Override
    public List<Post> findPosts(Request request) {
        Query query = new Query();
        query.with(new PageRequest(request.getPageOffset(),request.getPageLimit()));
        if(request.getSortKey()==null || request.getSortKey().isEmpty()){
            request.setSortKey("createdTime");
        }
        query.with(new Sort(new Sort.Order(Sort.Direction.valueOf(request.getSortOrder().name()),request.getSortKey())));
        return mongoOperations.find(query,Post.class,"post");
    }

    @Override
    public Post findPost(String postId) {
        return mongoOperations.findById(postId,Post.class,"post");
    }

    @Override
    public List<Post> findPostsByIds(List<String> postIds) {
        return mongoOperations.find(Query.query(Criteria.where("postId").in(postIds)),Post.class,"post");
    }

    @Override
    public Post updatePost(String wallPostId, Post post) {
        Post currentPost = mongoOperations.findById(post.getPostId(),Post.class,"post");
        if(currentPost!=null){
            post.setModifiedTime(new Date());
            mongoOperations.save(post,"post");
        }
        throw new AssetNotFoundException("Post not found with post Id "+post);
    }

    @Override
    public void deletePosts(List<String> posts){
        mongoOperations.remove(Query.query(Criteria.where("postId").in(posts)),Post.class,"post");
    }
}
