package com.easy.work.tab.dto;


import com.easy.core.domain.Domain;

/**
 * Created by apple on 18/5/17.
 */
public class TabFieldTypeDto extends Domain{

    private static final String SQL = "com.easy.work.tab.dao.TabFieldTypeDao.";

    @Override
    public String getSqlId() {
        return SQL;
    }
}
