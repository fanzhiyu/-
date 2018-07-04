package com.easy.work.role.dto;

import com.easy.core.domain.Domain;

/**
 * Created by apple on 18/7/2.
 */
public class RoleMenuDto extends Domain {

    public static final String SQL_ID = "com.easy.work.role.dao.RoleMenuDao.";

    @Override
    public String getSqlId() {
        return SQL_ID;
    }
}
