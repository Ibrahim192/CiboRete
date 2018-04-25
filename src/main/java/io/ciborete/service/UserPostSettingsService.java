package io.ciborete.service;

import io.ciborete.dto.Request;
import io.ciborete.model.UserPostSettings;

import java.util.List;

public interface UserPostSettingsService {
    UserPostSettings addUserPostSettings(UserPostSettings userPostSettings);
    void deleteUserPostSettings(String userId);
    UserPostSettings findUserPostSettings(String userId);
    UserPostSettings updateUserPostSettings(String userId,UserPostSettings userPostSettings);
}
