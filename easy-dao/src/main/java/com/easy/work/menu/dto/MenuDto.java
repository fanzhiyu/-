package com.easy.work.menu.dto;

import com.easy.core.domain.Domain;

import java.util.List;

/**
 * Created by apple on 18/6/28.
 */
public class MenuDto extends Domain {

    private static final String SQL_ID = "com.easy.work.menu.dao.MenuDao.";

    private String orgId;

    private String createUser;

    private List<String> roleIds;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }

    @Override
    public String getSqlId() {
        return SQL_ID;
    }
}
