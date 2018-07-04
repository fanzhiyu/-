package com.easy.work.province.dto;

import com.easy.core.domain.Domain;

/**
 * Created by apple on 18/6/15.
 */
public class ProvinceDto extends Domain {

    private static final String SQL_ID = "com.easy.work.province.dao.ProvinceDao.";

    @Override
    public String getSqlId() {
        return SQL_ID;
    }
}
