package com.easy.work.role.dto;

import com.easy.core.domain.Domain;

/**
 * Created by apple on 18/6/28.
 */
public class RoleOrgDto extends Domain {

    private static final String SQL_ID = "com.easy.work.role.dao.RoleOrgDao.";

    @Override
    public String getSqlId() {
        return SQL_ID;
    }
}
