package io.ciborete.controllers;

import io.ciborete.model.WallPost;
import io.ciborete.service.ReviewService;
import io.ciborete.service.WallPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/feeds")
public class FeedController {

    @Autowired
    WallPostService wallPostService;

    @Autowired
    ReviewService reviewService;

    @RequestMapping(path = "/wallposts",method = RequestMethod.POST)
    public List<WallPost> getPosts(){
        return null;
    }

}
