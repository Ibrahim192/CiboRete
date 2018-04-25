package io.ciborete.controllers;

import io.ciborete.dto.Request;
import io.ciborete.model.WallPost;
import io.ciborete.service.WallPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/wallPost")
public class WallPostController {

    @Autowired
    WallPostService wallPostService;
    @RequestMapping(path="/add",method = {RequestMethod.POST})
    public ResponseEntity<?> addWallPost(@RequestBody WallPost wallPost) {
        WallPost createdWallPost = wallPostService.addWallPost(wallPost);
        return (createdWallPost != null) ?
                (new ResponseEntity<WallPost>(createdWallPost, HttpStatus.CREATED)) :
                (new ResponseEntity<WallPost>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(method = {RequestMethod.GET})
    public List<WallPost> findWallPosts() {
        return wallPostService.findWallPosts();
    }

    @RequestMapping(path = "/{wallPostId}", method = RequestMethod.GET)
    public ResponseEntity<WallPost> findWallPost(@PathVariable String wallPostId) {
        WallPost wallPost = wallPostService.findWallPost(wallPostId);
        return (wallPost != null) ?
                new ResponseEntity<WallPost>(wallPost, HttpStatus.OK) :
                new ResponseEntity<WallPost>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path ="/ids",method = RequestMethod.POST)
    public List<WallPost> findWallPostByIds(@RequestBody List<String> wallPostIds) {
        return wallPostService.findWallPostsByIds(wallPostIds);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<List<WallPost>> findWallPosts(@RequestBody Request request) {
        System.out.println(request.toString());
        if(request==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(wallPostService.findWallPosts(request),HttpStatus.OK);
    }

    @RequestMapping(path = "/{wallPostId}", method = RequestMethod.PUT)
    public WallPost updateWallPost(@PathVariable String wallPostId, @RequestBody WallPost wallPost) {
        return wallPostService.updateWallPost(wallPostId, wallPost);
    }

    @RequestMapping(path = "/{wallPostId}", method = RequestMethod.DELETE)
    public void deleteWallPost(@PathVariable String wallPostId) {
        wallPostService.deleteWallPost(wallPostId);
    }
}
