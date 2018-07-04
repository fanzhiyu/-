package com.easy.work.org.dto;

import com.easy.core.domain.Domain;

/**
 * Created by apple on 18/6/28.
 */
public class OrgMenuDto extends Domain {

    private static final String SQL_ID = "com.easy.work.org.dao.OrgMenuDao.";

    @Override
    public String getSqlId() {
        return SQL_ID;
    }
}
