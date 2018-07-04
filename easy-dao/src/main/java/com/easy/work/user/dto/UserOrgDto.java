package com.easy.work.user.dto;

import com.easy.core.domain.PagingDomain;
import com.easy.work.user.domain.UserOrgDomain;

/**
 * Created by apple on 18/4/16.
 */
public class UserOrgDto extends PagingDomain<UserOrgDomain> {

    private static final String SQL_ID = "com.easy.work.user.dao.UserOrgDao.";

    @Override
    public String getSqlId() {
        return SQL_ID;
    }
}
