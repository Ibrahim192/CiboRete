package io.ciborete.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "usersettings")
public class UserSettings {

    @Indexed
    String userId;

    boolean allowSearch;

    boolean allowMails;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isAllowSearch() {
        return allowSearch;
    }

    public void setAllowSearch(boolean allowSearch) {
        this.allowSearch = allowSearch;
    }

    public boolean isAllowMails() {
        return allowMails;
    }

    public void setAllowMails(boolean allowMails) {
        this.allowMails = allowMails;
    }

    public boolean isAllowMessages() {
        return allowMessages;
    }

    public void setAllowMessages(boolean allowMessages) {
        this.allowMessages = allowMessages;
    }

    public boolean isShowDOB() {
        return showDOB;
    }

    public void setShowDOB(boolean showDOB) {
        this.showDOB = showDOB;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    boolean allowMessages;

    boolean showDOB;

    Date modifiedTime;



}
