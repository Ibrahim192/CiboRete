package io.ciborete.model;

import io.ciborete.enums.FriendShipStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class FriendShip {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Id
    String id;

    String userId1;

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

    public Date getModifiedStatusTime() {
        return modifiedStatusTime;
    }

    public void setModifiedStatusTime(Date modifiedStatusTime) {
        this.modifiedStatusTime = modifiedStatusTime;
    }

    public FriendShipStatus getStatus() {
        return status;
    }

    public void setStatus(FriendShipStatus status) {
        this.status = status;
    }

    String userId2;

    Date modifiedStatusTime;

    FriendShipStatus status;

}
