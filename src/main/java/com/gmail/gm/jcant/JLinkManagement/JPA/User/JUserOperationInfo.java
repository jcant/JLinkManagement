package com.gmail.gm.jcant.JLinkManagement.JPA.User;

public class JUserOperationInfo {
    private String message;
    private boolean success;
    private JUser user;

    public JUserOperationInfo() {
    }

    public JUserOperationInfo(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public JUserOperationInfo(String message, boolean success, JUser user) {
        this.message = message;
        this.success = success;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public JUser getUser() {
        return user;
    }

    public void setUser(JUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "JUserOperationInfo{" +
                "message='" + message + '\'' +
                ", success=" + success +
                ", user=" + user +
                '}';
    }
}
