package io.ciborete.controllers;


import io.ciborete.dto.FriendRequestBean;
import io.ciborete.helper.CurrentLoggedInUser;
import io.ciborete.model.FriendShip;
import io.ciborete.service.FriendService;
import org.apache.catalina.startup.UserConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/friends")
public class FriendsController {

    @Autowired
    FriendService friendService;

    @RequestMapping(path="initiate-request",method = RequestMethod.POST)
    public void initiateFriendRequest(@RequestBody FriendRequestBean request){
        friendService.initiateFriendshipRequest(request,CurrentLoggedInUser.getCurrentLoggedInUser().getUserId());
    }

    @RequestMapping(path="cancel-request",method = RequestMethod.POST)
    public void cancelFriendRequest(@RequestBody FriendRequestBean request){
        friendService.cancelFriendRequest(request,CurrentLoggedInUser.getCurrentLoggedInUser().getUserId());
    }

    @RequestMapping(path="hold-request",method = RequestMethod.POST)
    public void holdFriendRequest(@RequestBody FriendRequestBean request){
        friendService.holdFriendRequest(request,CurrentLoggedInUser.getCurrentLoggedInUser().getUserId());
    }

    @RequestMapping(path="confirm-request",method = RequestMethod.POST)
    public void confirmFriendRequest(@RequestBody FriendRequestBean request){
        friendService.confirmFriendRequest(request,CurrentLoggedInUser.getCurrentLoggedInUser().getUserId());
    }

    @RequestMapping(path="fetch-friends",method = RequestMethod.GET)
    public Set<String> fetchFriends(){
        return friendService.fetchFriends(CurrentLoggedInUser.getCurrentLoggedInUser().getUserId()).keySet();
    }

    @RequestMapping(path="fetch-friends/page/{pageNo}/offset/{offset}",method = RequestMethod.GET)
    public Set<String> fetchFriends(@PathVariable int pageNo,@PathVariable int offset){
        return friendService.fetchFriends(CurrentLoggedInUser.getCurrentLoggedInUser().getUserId(),pageNo,offset).keySet();
    }

    @RequestMapping(path="fetch-pending-requests",method = RequestMethod.GET)
    public List<FriendShip> fetchPendingRequests(){
        return friendService.fetchPendingRequests(CurrentLoggedInUser.getCurrentLoggedInUser().getUserId());
    }

    @RequestMapping(path="fetch-friends",method = RequestMethod.GET)
    public List<FriendShip> fetchSentRequests(){
        return friendService.fetchSentRequests(CurrentLoggedInUser.getCurrentLoggedInUser().getUserId());
    }


}
