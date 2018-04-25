package io.ciborete.service;

import io.ciborete.dto.Request;
import io.ciborete.exceptions.AssetNotFoundException;
import io.ciborete.model.User;

import java.util.List;

public interface UserService {

    User addUser(User user);
    void deleteUser(String UserId) ;
    List<User> findUsers();
    List<User> findUsers(Request request);
    User findUser(String userId);
    List<User> findUsersByIds(List<String> userIds);
    User updateUser(String userId,User user) throws AssetNotFoundException;
    void deleteUsers(List<String> userIds);
}
