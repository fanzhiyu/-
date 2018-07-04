package com.easy.work.city.dto;

import com.easy.core.domain.Domain;

/**
 * Created by apple on 18/6/15.
 */
public class CityDto extends Domain {

    private static final String SQL_ID = "com.easy.work.city.dao.CityDao.";

    @Override
    public String getSqlId() {
        return SQL_ID;
    }
}
