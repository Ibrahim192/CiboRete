package io.ciborete.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Document
public class Friends {

    @Indexed
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


