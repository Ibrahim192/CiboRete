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

    @RequestMapping(path="/initiate-request",method = RequestMethod.POST)
    public void initiateFriendRequest(@RequestBody FriendRequestBean request){
        friendService.initiateFriendshipRequest(request,CurrentLoggedInUser.getCurrentLoggedInUser().getUserId());
    }

    @RequestMapping(path="/update-request/{requestId}",method = RequestMethod.POST)
    public void updateFriendRequest(@PathVariable String requestId,@RequestBody FriendRequestBean request){
        friendService.updateFriendRequest(request,CurrentLoggedInUser.getCurrentLoggedInUser().getUserId());
    }

    @RequestMapping(path="/confirm-request/{requestId}",method = RequestMethod.POST)
    public void confirmFriendRequest(@PathVariable String requestId,@RequestBody FriendRequestBean request){
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

    @RequestMapping(path="fetch-sent-requests",method = RequestMethod.GET)
    public List<FriendShip> fetchSentRequests(){
        return friendService.fetchSentRequests(CurrentLoggedInUser.getCurrentLoggedInUser().getUserId());
    }


}
