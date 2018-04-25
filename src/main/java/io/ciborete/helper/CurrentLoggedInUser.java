package io.ciborete.helper;

import io.ciborete.enums.UserType;

public class CurrentLoggedInUser {

    private static LoggedInUser loggedInUser;

    public static LoggedInUser getCurrentLoggedInUser(){
        return loggedInUser;
    }

    public static void setLoggedInUser(LoggedInUser user){
        loggedInUser = user;
    }

    public static void setLoggedInUser(String userId, UserType userType){
        loggedInUser = new LoggedInUser(userId,userType);
    }
}
