package io.ciborete.service;

import io.ciborete.dto.FriendRequestBean;
import io.ciborete.model.FriendShip;
import io.ciborete.model.Friends;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface FriendService {
   void initiateFriendshipRequest(FriendRequestBean requestBean, String requestor);

    void confirmFriendRequest(FriendRequestBean requestBean, String requestor);

    void cancelFriendRequest(FriendRequestBean requestBean, String requestor);

    void holdFriendRequest(FriendRequestBean requestBean, String requestor);

    void remove(String userId1, String userId2);

    Map<String,Date> fetchFriends(String userId);

    Map<String,Date> fetchFriends(String userId, int page, int offset);

    List<FriendShip> fetchPendingRequests(String userId);

    List<FriendShip> fetchSentRequests(String userId);
}