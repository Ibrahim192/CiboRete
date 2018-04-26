package io.ciborete.service;

import io.ciborete.dto.Request;
import io.ciborete.exceptions.AssetNotFoundException;
import io.ciborete.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    MongoOperations mongoOperations;

    @Override
    public User addUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        user.setCreatedTime(new Date());
        user.setModifiedTime(new Date());
        mongoOperations.save(user);
        return user;
    }

    @Override
    public void deleteUser(String userId) throws AssetNotFoundException {
        User user = mongoOperations.findOne(Query.query(Criteria.where("userId").is(userId)),User.class);
        if(user!=null){
            mongoOperations.remove(Query.query(Criteria.where("userId").is(userId)),User.class);
        }
        else {
            throw new AssetNotFoundException("User not found with user Id "+userId);
        }
    }

    @Override
    public void deleteUsers(List<String> userIds) {
        mongoOperations.remove(Query.query(Criteria.where("userId").in(userIds)));
    }

    @Override
    public List<User> findUsers() {
        return mongoOperations.findAll(User.class);
    }

    @Override
    public List<User> findUsers(Request request) {
        Query query = new Query();
        query.with(new PageRequest(request.getPageOffset(),request.getPageLimit()));
        if(request.getSortKey()==null || request.getSortKey().isEmpty()){
            request.setSortKey("createdTime");
        }
        query.with(new Sort(new Sort.Order(Sort.Direction.valueOf(request.getSortOrder().name()),request.getSortKey())));
        return mongoOperations.find(query,User.class);
    }

    @Override
    public User findUser(String userId) {
        return mongoOperations.findById(userId,User.class);
    }

    @Override
    public List<User> findUsersByIds(List<String> userIds) {
        return mongoOperations.find(Query.query(Criteria.where("userId").in(userIds)),User.class);

    }

    @Override
    public User updateUser(String userId, User user) throws AssetNotFoundException {
        User currentUser = mongoOperations.findById(user.getUserId(),User.class);
        if(currentUser!=null){
            user.setModifiedTime(new Date());
            mongoOperations.save(user);
        }
        throw new AssetNotFoundException("User not found with user Id "+userId);
    }
}
