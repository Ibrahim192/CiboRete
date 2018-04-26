package io.ciborete.model;

import io.ciborete.enums.Props;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Map;

@Document(collection = "userpostsettings")
public class UserPostSettings {

    @Indexed
    String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map<Props, Object> getSettings() {
        return settings;
    }

    public void setSettings(Map<Props, Object> settings) {
        this.settings = settings;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    Map<Props,Object> settings;

    Date modifiedTime;




}
