package io.ciborete.dto;

import io.ciborete.enums.FriendShipStatus;

import java.util.Date;

public class FriendRequestBean {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;

    Date initiationTime;

    String userId;

    public Date getInitiationTime() {
        return initiationTime;
    }

    public void setInitiationTime(Date initiationTime) {
        this.initiationTime = initiationTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public FriendShipStatus getStatus() {
        return status;
    }

    public void setStatus(FriendShipStatus status) {
        this.status = status;
    }

    FriendShipStatus status;

}
