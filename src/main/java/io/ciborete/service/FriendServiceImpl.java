package io.ciborete.service;


import io.ciborete.dto.FriendRequestBean;
import io.ciborete.dto.FriendshipResponse;
import io.ciborete.enums.FriendShipStatus;
import io.ciborete.model.FriendShip;
import io.ciborete.model.Friends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    MongoOperations mongoOperations;

    @Override
    public void initiateFriendshipRequest(FriendRequestBean requestBean, String requestor) {
        FriendShip friendShip = new FriendShip();
        friendShip.setStatus(FriendShipStatus.INITIATED);
        friendShip.setUserId1(requestor);
        friendShip.setUserId2(requestBean.getUserId());
        friendShip.setModifiedStatusTime(requestBean.getInitiationTime());
        mongoOperations.save(friendShip,"friendship");
    }

    @Override
    public void confirmFriendRequest(FriendRequestBean requestBean, String requestor){
        FriendShip friendShip = mongoOperations.findOne(new Query(Criteria.where("userId1").is(requestBean.getUserId())).addCriteria(Criteria.where("userId2").is(requestor)),FriendShip.class);
        friendShip.setModifiedStatusTime(requestBean.getInitiationTime());
        friendShip.setStatus(FriendShipStatus.CONFIRMED);
        mongoOperations.save(friendShip,"friendship");
        Friends updateForFriend1 = mongoOperations.findOne(Query.query(Criteria.where("userId").is(requestBean.getUserId())),Friends.class,"friends");
        Friends updateForFriend2 = mongoOperations.findOne(Query.query(Criteria.where("userId").is(requestor)),Friends.class,"friends");
        if(updateForFriend1!=null){
            updateForFriend1.getFriends().put(requestor,new Date());
        }
        else {
            updateForFriend1 = new Friends();
            updateForFriend1.setUserId(requestBean.getUserId());
            updateForFriend1.setFriends(Map.of(requestor,new Date()));
        }
        if(updateForFriend2!=null){
            updateForFriend2.getFriends().put(requestBean.getUserId(),new Date());
        }
        else {
            updateForFriend2 = new Friends();
            updateForFriend2.setUserId(requestor);
            updateForFriend2.setFriends(Map.of(requestBean.getUserId(),new Date()));
        }
        mongoOperations.save(updateForFriend1,"friends");
        mongoOperations.save(updateForFriend2,"friends");
        mongoOperations.remove(new Query(Criteria.where("userId1").is(requestBean.getUserId())).addCriteria(Criteria.where("userId2").is(requestor)),FriendShip.class,"frienship");
    }

    @Override
    public void cancelFriendRequest(FriendRequestBean requestBean, String requestor){
        FriendShip friendShip = mongoOperations.findOne(new Query(Criteria.where("userId1").is(requestBean.getUserId())).addCriteria(Criteria.where("userId2").is(requestor)),FriendShip.class);
        friendShip.setModifiedStatusTime(requestBean.getInitiationTime());
        friendShip.setStatus(FriendShipStatus.CANCELLED);
        mongoOperations.save(friendShip,"friendship");
    }

    @Override
    public void holdFriendRequest(FriendRequestBean requestBean, String requestor){
        FriendShip friendShip = mongoOperations.findOne(new Query(Criteria.where("userId1").is(requestBean.getUserId())).addCriteria(Criteria.where("userId2").is(requestor)),FriendShip.class);
        friendShip.setModifiedStatusTime(requestBean.getInitiationTime());
        friendShip.setStatus(FriendShipStatus.HOLD);
        mongoOperations.save(friendShip,"friendship");
    }

    @Override
    public void remove(String userId1, String userId2){
        Friends user1Friends = mongoOperations.findOne(Query.query(Criteria.where("userId").is(userId1)),Friends.class);
        user1Friends.getFriends().remove(userId2);
        mongoOperations.save(user1Friends,"friends");
        Friends user2Friends = mongoOperations.findOne(Query.query(Criteria.where("userId").is(userId2)),Friends.class);
        user2Friends.getFriends().remove(userId2);
        mongoOperations.save(user2Friends,"friends");
    }

    @Override
    public Map<String,Date> fetchFriends(String userId){
        Friends friends = mongoOperations.findOne(Query.query(Criteria.where("userId").is(userId)),Friends.class,"friends");
        return friends.getFriends();
    }

    @Override
    public Map<String,Date> fetchFriends(String userId, int page, int offset){
        Friends friends = mongoOperations.findOne(new Query(Criteria.where("userId").is(userId)).with(new PageRequest(page,offset)),Friends.class,"friends");
        return friends.getFriends();
    }

    @Override
    public List<FriendShip> fetchPendingRequests(String userId){
        List<FriendShip> friends = mongoOperations.find(new Query(Criteria.where("userId2").is(userId)).addCriteria(Criteria.where("status").in(Arrays.asList(FriendShipStatus.HOLD.name(),FriendShipStatus.INITIATED.name()))),FriendShip.class,"friendship");
        return friends;
    }

    @Override
    public List<FriendShip> fetchSentRequests(String userId){
        List<FriendShip> friends = mongoOperations.find(new Query(Criteria.where("userId1").is(userId)).addCriteria(Criteria.where("status").in(Arrays.asList(FriendShipStatus.HOLD.name(),FriendShipStatus.INITIATED.name()))),FriendShip.class,"friendship");
        return friends;
    }


}
