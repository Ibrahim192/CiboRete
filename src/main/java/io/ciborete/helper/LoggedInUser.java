package io.ciborete.helper;

import io.ciborete.enums.UserType;

public class LoggedInUser {

    public LoggedInUser(String userId, UserType userType) {
        this.userId = userId;
        this.userType = userType;
    }

    String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    UserType userType;
}
