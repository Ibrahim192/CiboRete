package io.ciborete.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class FriendDetail {

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    @Id
    String friendId;

    public Date getDateOfFriendShip() {
        return dateOfFriendShip;
    }

    public void setDateOfFriendShip(Date dateOfFriendShip) {
        this.dateOfFriendShip = dateOfFriendShip;
    }

    Date dateOfFriendShip;

}
