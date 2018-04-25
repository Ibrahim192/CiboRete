package io.ciborete.service;

import io.ciborete.exceptions.AssetNotFoundException;
import io.ciborete.helper.CurrentLoggedInUser;
import io.ciborete.model.UserSettings;
import io.ciborete.model.UserSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserSettingsServiceImpl implements UserSettingsService {
    @Autowired
    MongoOperations mongoOperations;

    @Override
    public UserSettings addUserSettings(UserSettings userSettings) {
        userSettings.setModifiedTime(new Date());
        mongoOperations.save(userSettings,"userSettings");
        return userSettings;
    }

    @Override
    public void deleteUserSettings(String userId) {
        UserSettings userSettings = mongoOperations.findById(userId,UserSettings.class,"userSettings");
        if(userSettings!=null){
            mongoOperations.remove(Query.query(Criteria.where("userId").is(userId)),UserSettings.class,"userSettings");
        }
        else {
            throw new AssetNotFoundException("User not found with user Id "+userId);
        }
    }

    @Override
    public UserSettings findCurrentUserSettings( ) {
        String userId= CurrentLoggedInUser.getCurrentLoggedInUser().getUserId();
        return mongoOperations.findOne(Query.query(Criteria.where("userId").is(userId)),UserSettings.class,"userSettings");
    }

    @Override
    public UserSettings updateUserSettings(String userId, UserSettings userSettings) {
        UserSettings currentUserSettings = mongoOperations.findOne(Query.query(Criteria.where("userId").is(userId)),UserSettings.class,"userSettings");
        if(currentUserSettings!=null){
            userSettings.setModifiedTime(new Date());
            mongoOperations.save(userSettings,"userSettings");
        }
        else {
            throw new AssetNotFoundException("User Settings not found with user Id " + userId);
        }
        return userSettings;
    }
}
