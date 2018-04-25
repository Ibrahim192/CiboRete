package io.ciborete.config;

public class EndUserConfig {

    String userId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    String email;

    boolean sendMail = false;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isSendMail() {
        return sendMail;
    }

    public void setSendMail(boolean sendMail) {
        this.sendMail = sendMail;
    }
}
