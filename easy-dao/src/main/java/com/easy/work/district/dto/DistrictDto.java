package com.easy.work.district.dto;

import com.easy.core.domain.Domain;

/**
 * Created by apple on 18/6/15.
 */
public class DistrictDto extends Domain {

    private static final String SQL_ID = "com.easy.work.district.dao.DistrictDao.";

    @Override
    public String getSqlId() {
        return SQL_ID;
    }
}
