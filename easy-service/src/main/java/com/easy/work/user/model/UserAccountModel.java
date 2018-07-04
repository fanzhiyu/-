package com.easy.work.user.model;

/**
 * Created by apple on 18/6/25.
 */
public class UserAccountModel {

    private String userId;
    private String loginName;
    private String password;
    private String status;
    private String accountLock;
    private String accountId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccountLock() {
        return accountLock;
    }

    public void setAccountLock(String accountLock) {
        this.accountLock = accountLock;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
