package com.easy.work.user.dto;

import com.easy.core.domain.Domain;

/**
 * Created by apple on 18/6/25.
 */
public class UserAccountDto extends Domain {

    private static final String SQL_ID = "com.easy.work.user.dao.UserAccountDao.";

    private String userName;
    private String orgId;
    private String userCode;
    private String userTel;
    private String userEmail;
    private String parentId;
    private String orgNo;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    @Override
    public String getSqlId() {
        return SQL_ID;
    }
}
