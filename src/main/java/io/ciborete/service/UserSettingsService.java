package io.ciborete.service;

import io.ciborete.model.UserSettings;

public interface UserSettingsService {

    UserSettings addUserSettings(UserSettings userSettings);
    void deleteUserSettings(String userId);
    UserSettings findCurrentUserSettings();
    UserSettings updateUserSettings(String userId,UserSettings userSettings);
}
