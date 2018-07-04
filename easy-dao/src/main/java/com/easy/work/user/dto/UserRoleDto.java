package com.easy.work.user.dto;

import com.easy.core.domain.Domain;

/**
 * Created by apple on 18/6/21.
 */
public class UserRoleDto extends Domain{

    private static final String SQL_ID = "com.easy.work.user.dao.UserRoleDao.";

    @Override
    public String getSqlId() {
        return SQL_ID;
    }
}
