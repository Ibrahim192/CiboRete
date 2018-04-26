package io.ciborete.service;


import io.ciborete.dto.Request;
import io.ciborete.exceptions.AssetNotFoundException;
import io.ciborete.model.User;
import io.ciborete.model.UserPostSettings;
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
public class UserPostSettingsServiceImpl implements UserPostSettingsService {
    @Autowired
    MongoOperations mongoOperations;

    @Override
    public UserPostSettings addUserPostSettings(UserPostSettings userPostSettings) {
        userPostSettings.setModifiedTime(new Date());
        mongoOperations.save(userPostSettings);
        return userPostSettings;
    }

    @Override
    public void deleteUserPostSettings(String userId) {
        UserPostSettings userPostSettings = mongoOperations.findById(userId,UserPostSettings.class);
        if(userPostSettings!=null){
            mongoOperations.remove(Query.query(Criteria.where("userId").is(userId)),UserPostSettings.class);
        }
        else {
            throw new AssetNotFoundException("User not found with user Id "+userId);
        }
    }




    @Override
    public UserPostSettings findUserPostSettings(String userId) {
        return mongoOperations.findById(userId,UserPostSettings.class);
    }


    @Override
    public UserPostSettings updateUserPostSettings(String userId, UserPostSettings userPostSettings) {
        UserPostSettings currentUserPostSettings = mongoOperations.findById(userPostSettings.getUserId(),UserPostSettings.class);
        if(currentUserPostSettings!=null){
            userPostSettings.setModifiedTime(new Date());
            mongoOperations.save(userPostSettings);
        }
        throw new AssetNotFoundException("User Post Settings not found with user Id "+userId);
    }
}
