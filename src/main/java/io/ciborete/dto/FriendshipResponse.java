package io.ciborete.dto;

import java.util.Date;

public class FriendshipResponse {

    String userId1;

    String userId2;

    boolean areFriends;

    public String getUserId1() {
        return userId1;
    }

    public void setUserId1(String userId1) {
        this.userId1 = userId1;
    }

    public String getUserId2() {
        return userId2;
    }

    public void setUserId2(String userId2) {
        this.userId2 = userId2;
    }

    public boolean isAreFriends() {
        return areFriends;
    }

    public void setAreFriends(boolean areFriends) {
        this.areFriends = areFriends;
    }

    public Date getDateOfFriendShip() {
        return dateOfFriendShip;
    }

    public void setDateOfFriendShip(Date dateOfFriendShip) {
        this.dateOfFriendShip = dateOfFriendShip;
    }

    Date dateOfFriendShip;
}
