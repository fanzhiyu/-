package com.easy.work.sign.dto;

import com.easy.core.domain.PagingDomain;
import com.easy.work.sign.domain.SignDomain;

/**
 * Created by apple on 18/5/2.
 */
public class SignDto extends PagingDomain<SignDomain> {


    private static final String SQL_ID = "com.easy.work.sign.dao.SignDao.";


    @Override
    public String getSqlId() {
        return SQL_ID;
    }
}
