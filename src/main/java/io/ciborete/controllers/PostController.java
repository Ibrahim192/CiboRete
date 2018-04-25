package io.ciborete.controllers;

import io.ciborete.adapters.PostAdapter;
import io.ciborete.dto.Request;
import io.ciborete.model.Post;
import io.ciborete.model.Review;
import io.ciborete.model.WallPost;
import io.ciborete.service.PostService;
import io.ciborete.service.ReviewService;
import io.ciborete.service.WallPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/posts")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    WallPostService wallPostService;

    @Autowired
    ReviewService reviewService;


    @RequestMapping(path="/add",method = {RequestMethod.POST})
    public ResponseEntity<?> addPost(@RequestBody Post post) {
        Post createdPost = postService.addPost(post);
        if(createdPost==null){
            return new ResponseEntity<Post>(HttpStatus.BAD_REQUEST);
        }
        WallPost createdWallPost=null;
        Review review = null;
        WallPost wallPost = PostAdapter.adaptToWallPost(post);
        review = PostAdapter.adaptToReview(post);
        createdWallPost = wallPostService.addWallPost(wallPost);
        Review createdReview = (review!=null)? reviewService.addReview(review) : null;
        if(createdWallPost==null && createdReview ==null){
            postService.deletePost(createdPost.getPostId());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Post>(createdPost, HttpStatus.CREATED);
    }

    @RequestMapping(method = {RequestMethod.GET})
    public List<Post> findPosts() {
        return postService.findPosts();
    }

    @RequestMapping(path = "/{postId}", method = RequestMethod.GET)
    public ResponseEntity<Post> findPost(@PathVariable String postId) {
        Post post = postService.findPost(postId);
        return (post != null) ?
                new ResponseEntity<>(post, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path ="/ids",method = RequestMethod.POST)
    public List<Post> findPostsByIds(@RequestBody List<String> postIds) {
        return postService.findPostsByIds(postIds);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<List<Post>> findPosts(@RequestBody Request request) {
        System.out.println(request.toString());
        if(request==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(postService.findPosts(request),HttpStatus.OK);
    }

    @RequestMapping(path = "/{postId}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable String postId) {
        postService.deletePost(postId);
    }

}
