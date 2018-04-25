package io.ciborete.model;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Friends {

    @Id
    String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map<String,Date> getFriends() {
        return friends;
    }

    public void setFriends(Map<String,Date> friends) {
        this.friends = friends;
    }

    Map<String,Date> friends;



}


